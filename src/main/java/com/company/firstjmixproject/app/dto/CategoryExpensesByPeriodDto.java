package com.company.firstjmixproject.app.dto;

import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@JmixEntity(name = "idid_CategoryExpensesDto")
public class CategoryExpensesByPeriodDto {
    @JmixGeneratedValue
    @JmixId
    private UUID id;

    @InstanceName
    @JmixProperty(mandatory = true)
    @NotNull
    private OperationCategory category;

    @JmixProperty(mandatory = true)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date start;

    @JmixProperty(mandatory = true)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date end;


    public CategoryExpensesByPeriodDto(OperationCategory operationCategory,
                                       Date start,
                                       Date end){
        this.category = operationCategory;
        this.start = start;
        this.end = end;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public OperationCategory getCategory() {
        return category;
    }

    public void setCategory(OperationCategory category) {
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}