package com.company.firstjmixproject.app.service.category;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import com.company.firstjmixproject.entity.OperationCategory;

import java.math.BigDecimal;

public interface CategoryExpenseService {
    BigDecimal byPeriod(CategoryExpensesByPeriodDto ce);
    BigDecimal all(OperationCategory operationCategory);
}
