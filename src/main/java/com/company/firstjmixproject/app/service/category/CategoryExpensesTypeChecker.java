package com.company.firstjmixproject.app.service.category;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import org.springframework.stereotype.Service;

@Service("idid_CategoryExpensesPeriodChecker")
public class CategoryExpensesTypeChecker {
    public boolean withPeriod(CategoryExpensesDto categoryExpensesDto){
        CategoryExpensesByPeriodDto byPeriodDto = ((CategoryExpensesByPeriodDto) categoryExpensesDto);
        return byPeriodDto.getStart() != null
                && byPeriodDto.getEnd() != null;
    }

}
