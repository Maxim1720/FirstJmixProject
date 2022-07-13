
drop table if exists idid_operation;
drop table if exists idid_operation_category;
drop table if exists idid_bill;
drop table if exists idid_bill_currency;



create table idid_bill_currency
(
    id                 uuid        not null
        constraint pk_idid_bill_currency
            primary key,
    name               varchar(10) not null,
    version            integer     not null,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    deleted_by         varchar(255),
    deleted_date       timestamp
);

alter table idid_bill_currency
    owner to postgres;

create unique index idx_uc_idid_bill_currency_name
    on idid_bill_currency (name)
    where (deleted_date IS NULL);


create table idid_bill
(
    id                 uuid           not null
        constraint pk_idid_bill
            primary key,
    currency_id        uuid           not null
        constraint fk_idid_bill_on_currency
            references idid_bill_currency,
    funds              numeric(19, 2) not null,
    name               varchar(20)    not null,
    version            integer        not null,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    deleted_by         varchar(255),
    deleted_date       timestamp,
    owner_id           uuid           not null
        constraint fk_idid_bill_on_owner
            references idid_user
);

alter table idid_bill
    owner to postgres;

create index idx_bill_currency_id
    on idid_bill (currency_id);

create unique index idx_uc_idid_bill_owner
    on idid_bill (owner_id)
    where (deleted_date IS NULL);

create index idx_bill_owner_id
    on idid_bill (owner_id);

create unique index idx_uc_idid_bill_name
    on idid_bill (name)
    where (deleted_date IS NULL);


create table idid_operation_category
(
    id                 uuid        not null
        constraint pk_idid_operation_category
            primary key,
    version            integer     not null,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    deleted_by         varchar(255),
    deleted_date       timestamp,
    name               varchar(50) not null
);

alter table idid_operation_category
    owner to postgres;

create unique index idx_uc_idid_operation_category_name
    on idid_operation_category (name)
    where (deleted_date IS NULL);



create table idid_operation
(
    id                 uuid                  not null
        constraint pk_idid_operation
            primary key,
    version            integer               not null,
    created_by         varchar(255),
    created_date       timestamp,
    last_modified_by   varchar(255),
    last_modified_date timestamp,
    deleted_by         varchar(255),
    deleted_date       timestamp,
    bill_id            uuid                  not null
        constraint fk_idid_operation_on_bill
            references idid_bill,
    category_id        uuid
        constraint fk_idid_operation_on_category
            references idid_operation_category,
    sum_               numeric(19, 2)        not null,
    comment_           varchar(150),
    type_              varchar(255)          not null,
    canceled           boolean default false not null
);

alter table idid_operation
    owner to postgres;

create index idx_operation_category_id
    on idid_operation (category_id);

