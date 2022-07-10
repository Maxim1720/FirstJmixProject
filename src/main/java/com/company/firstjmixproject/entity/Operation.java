package com.company.firstjmixproject.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.annotation.DeletedBy;
import io.jmix.core.annotation.DeletedDate;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "IDID_OPERATION", indexes = {
        @Index(name = "IDX_OPERATION_CATEGORY_ID", columnList = "CATEGORY_ID")
})
@Entity(name = "idid_Operation")
public class Operation {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VERSION", nullable = false)
    @Version
    private Integer version;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @DeletedBy
    @Column(name = "DELETED_BY")
    private String deletedBy;

    @DeletedDate
    @Column(name = "DELETED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @InstanceName
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BILL_ID", nullable = false)
    @NotNull
    private Bill bill;

    @Column(name = "CANCELED", nullable = false, columnDefinition = "boolean default false")
    @NotNull
    private Boolean isCanceled = false;

    @JmixProperty(mandatory = true)
    @Column(name = "TYPE_", nullable = false)
    @NotNull
    private String type;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "CATEGORY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private OperationCategory category;

    @PositiveOrZero(message = "{msg://com.company.firstjmixproject.entity/Operation.sum.validation.PositiveOrZero}")
    @NotNull
    @Column(name = "SUM_", nullable = false, precision = 19, scale = 2)
    private BigDecimal sum;

    @Column(name = "COMMENT_", length = 150)
    private String comment;

    public Boolean getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(Boolean canceled) {
        this.isCanceled = canceled;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void setType(OperationType type) {
        this.type = type == null ? null : type.getId();
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public OperationType getType() {
        return type == null ? null : OperationType.fromId(type);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public OperationCategory getCategory() {
        return category;
    }

    public void setCategory(OperationCategory category) {
        this.category = category;
    }

    public Bill getBill() {
        return bill;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}