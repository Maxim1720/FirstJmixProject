package com.company.firstjmixproject.screen.categoryexpensesbyperioddto;

import com.company.firstjmixproject.app.service.category.CategoryExpenseService;
import com.company.firstjmixproject.screen.categorygeneralexpensesdto.CategoryGeneralExpensesDtoBrowse;
import io.jmix.ui.screen.*;
import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("idid_CategoryExpensesDto.edit")
@UiDescriptor("category-expenses-by-period-dto-edit.xml")
@EditedEntityContainer("categoryExpensesByPeriodDtoDc")
public class CategoryExpensesByPeriodDtoEdit extends StandardEditor<CategoryExpensesByPeriodDto> {

    @Getter
    private CategoryExpensesByPeriodDto categoryExpensesByPeriodDto;
    @Autowired
    private CategoryExpenseService expenseService;

    @Subscribe
    public void onBeforeClose(BeforeCloseEvent event) {
        categoryExpensesByPeriodDto = getEditedEntity();
        categoryExpensesByPeriodDto.setExpenses(
                expenseService.byPeriod(categoryExpensesByPeriodDto)
        );
    }

    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
//        event.closedWith(StandardOutcome.CLOSE);
        event.closedWith(StandardOutcome.DISCARD);
    }
}