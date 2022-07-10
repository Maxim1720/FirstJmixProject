package com.company.firstjmixproject.screen.categorygeneralexpensesdto;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import com.company.firstjmixproject.app.dto.CategoryExpensesDto;
import com.company.firstjmixproject.app.service.category.CategoryExpenseService;
import com.company.firstjmixproject.app.service.category.CategoryExpensesPeriodChecker;
import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.DataManager;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import io.jmix.ui.theme.ThemeClassNames;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UiController("idid_CategoryExpensesDto.browse")
@UiDescriptor("category-expenses-dto-browse.xml")
@LookupComponent("categoryGeneralExpensesDtoesTable")
public class CategoryExpensesDtoBrowse extends StandardLookup<CategoryExpensesDto> {
    @Autowired
    private CollectionContainer<CategoryExpensesByPeriodDto> categoryGeneralExpensesDtoesDc;
    @Autowired
    private GroupTable<CategoryExpensesByPeriodDto> categoryGeneralExpensesDtoesTable;
    @Autowired
    private Button expensesPeriodEditBtn;
    @Autowired
    private Button generalExpensesBtn;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CategoryExpenseService expenseService;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private CategoryExpensesPeriodChecker categoryExpensesPeriodChecker;
    private CategoryExpensesByPeriodDto selectedCategoryExpensesDto;

    @Subscribe("categoryGeneralExpensesDtoesTable.generalExpenses")
    public void onCategoryGeneralExpensesDtoesTableGeneralExpenses(Action.ActionPerformedEvent event) {
        selectedCategoryExpensesDto.setStart(null);
        selectedCategoryExpensesDto.setEnd(null);
        selectedCategoryExpensesDto.setExpenses(expenseService.all(selectedCategoryExpensesDto.getCategory()));
        setGeneralExpensesBtnsStyle();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        setGeneralExpenses();
        setGeneralExpensesBtnsStyle();
    }


    @Subscribe("categoryGeneralExpensesDtoesTable")
    public void onCategoryGeneralExpensesDtoesTableSelection(Table.SelectionEvent<CategoryExpensesByPeriodDto> event) {
        Optional<CategoryExpensesByPeriodDto> optionalCategoryExpensesDto = event.getSelected().stream().findFirst();
        optionalCategoryExpensesDto.ifPresent(
                categoryExpensesDto -> {
                    selectedCategoryExpensesDto = categoryExpensesDto;
                    setBtnsStyleFromExpensesType(selectedCategoryExpensesDto);
                }
        );
    }

    @Subscribe("categoryGeneralExpensesDtoesTable.periodEdit")
    public void onCategoryGeneralExpensesDtoesTablePeriodEdit(Action.ActionPerformedEvent event) {
        screenBuilders.editor(categoryGeneralExpensesDtoesTable).build().show().addAfterCloseListener(
                l->{
                    if(l.closedWith(StandardOutcome.COMMIT)){
                        setExpensesByPeriodBtnsStyle();
                    }
                }
        );
    }

    private void setGeneralExpenses(){
        categoryGeneralExpensesDtoesDc.getMutableItems().clear();
        List<OperationCategory> categories = dataManager.load(OperationCategory.class).all().list();
        categoryGeneralExpensesDtoesDc.getMutableItems()
                .addAll(initedCategoryExpensesDtoes(categories));
        categoryGeneralExpensesDtoesTable.
                sort(categoryGeneralExpensesDtoesTable.getColumns().get(0).getStringId(),
                        Table.SortDirection.ASCENDING);
    }

    private List<CategoryExpensesByPeriodDto> initedCategoryExpensesDtoes(List<OperationCategory> categories){

        List<CategoryExpensesByPeriodDto> categoryExpensesDtos = new ArrayList<>();

        for (OperationCategory category:
                categories) {
            CategoryExpensesByPeriodDto generalExpensesDto =
                    dataManager.create(CategoryExpensesByPeriodDto.class);
            generalExpensesDto.setCategory(category);
            generalExpensesDto.setExpenses(expenseService.all(category));
            categoryExpensesDtos.add(generalExpensesDto);
        }
        return categoryExpensesDtos;
    }
    private void setBtnsStyleFromExpensesType(CategoryExpensesByPeriodDto expensesDto){
        if(categoryExpensesPeriodChecker.withPeriod(expensesDto)){
            setExpensesByPeriodBtnsStyle();
        }
        else {
            setGeneralExpensesBtnsStyle();
        }
    }

    private void setExpensesByPeriodBtnsStyle(){
        expensesPeriodEditBtn.setStyleName(ThemeClassNames.BUTTON_PRIMARY);
        generalExpensesBtn.setStyleName(ThemeClassNames.BUTTON_QUIET);
    }
    private void setGeneralExpensesBtnsStyle(){
        generalExpensesBtn.setStyleName(ThemeClassNames.BUTTON_PRIMARY);
        expensesPeriodEditBtn.setStyleName(ThemeClassNames.BUTTON_QUIET);
    }
    
}