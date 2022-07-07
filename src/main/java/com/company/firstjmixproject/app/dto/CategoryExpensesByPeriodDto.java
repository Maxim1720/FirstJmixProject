package com.company.firstjmixproject.app.dto;

import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@JmixEntity(name = "idid_CategoryExpensesDto")
public class CategoryExpensesByPeriodDto extends CategoryExpensesDto {

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
        this.setCategory(operationCategory);
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

    @InstanceName
    @DependsOnProperties({"category"})
    public String getInstanceName() {
        return String.format("%s", getCategory());
    }
}