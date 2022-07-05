package com.company.firstjmixproject.screen.expenseseditfragment;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import com.company.firstjmixproject.app.service.category.CategoryExpenseService;
import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.DataManager;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.Field;
import io.jmix.ui.screen.ScreenFragment;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@UiController("idid_ExpensesEditFragment")
@UiDescriptor("expenses-edit-fragment.xml")
public class ExpensesEditFragment extends ScreenFragment {

    private static final Logger log = LoggerFactory.getLogger(ExpensesEditFragment.class);

    @Autowired
    private CategoryExpenseService expenseService;
    @Autowired
    private ExpensesEditFragmentNotificator expensesEditFragmentNotificator;
    @Autowired
    private DataManager dataManager;

    @Autowired
    private DateField<Date> startField;
    @Autowired
    private DateField<Date> endField;
    @Autowired
    private Field<OperationCategory> categoryField;

    private CategoryExpensesByPeriodDto categoryExpensesByPeriodDto;

    @Subscribe("expensesCalculate")
    public void onCalculateExpenses(Action.ActionPerformedEvent event) {

        //if(categoryExpensesDto != null){

        categoryExpensesByPeriodDto = dataManager.create(CategoryExpensesByPeriodDto.class);
        categoryExpensesByPeriodDto.setCategory(categoryField.getValue());
        categoryExpensesByPeriodDto.setStart(startField.getValue());
        categoryExpensesByPeriodDto.setEnd(endField.getValue());

        BigDecimal expenses = expenseService.byPeriod(categoryExpensesByPeriodDto);
        expensesEditFragmentNotificator.sendNotification(expenses.toPlainString());
        //}
        //log.error("categoryExpensesDto is null");
    }


}