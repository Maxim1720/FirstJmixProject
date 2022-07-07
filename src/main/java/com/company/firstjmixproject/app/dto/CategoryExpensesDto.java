package com.company.firstjmixproject.app.dto;

import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.JmixId;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.JmixProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity(name = "idid_CategoryGeneralExpensesDto")
public class CategoryExpensesDto {
    @JmixGeneratedValue
    @JmixId
    private UUID id;

    @InstanceName
    @JmixProperty(mandatory = true)
    @NotNull
    private OperationCategory category;

    @JmixProperty(mandatory = true)
    @NotNull
    private BigDecimal expenses;

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
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