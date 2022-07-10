package com.company.firstjmixproject.app.service.category;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import org.springframework.stereotype.Service;

@Service("idid_CategoryExpensesPeriodChecker")
public class CategoryExpensesPeriodChecker {

    public boolean withPeriod(CategoryExpensesByPeriodDto categoryExpensesByPeriodDto){
        return categoryExpensesByPeriodDto.getStart() != null
                && categoryExpensesByPeriodDto.getEnd() != null;
    }

}
