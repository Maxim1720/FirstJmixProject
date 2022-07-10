package com.company.firstjmixproject.screen.categorygeneralexpensesdto;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import com.company.firstjmixproject.app.service.category.CategoryExpenseService;
import com.company.firstjmixproject.entity.OperationCategory;
import com.company.firstjmixproject.screen.categoryexpensesbyperioddto.CategoryExpensesByPeriodDtoEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.Screens;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.firstjmixproject.app.dto.CategoryExpensesDto;
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
    DataManager dataManager;
    @Autowired
    CategoryExpenseService expenseService;
    @Autowired
    private Screens screens;
    private CategoryExpensesByPeriodDtoEdit editScreen;
    private CategoryExpensesDto selectedCategoryExpensesDto;
    @Autowired
    private GroupTable<CategoryExpensesDto> categoryGeneralExpensesDtoesTable;

    @Subscribe("categoryGeneralExpensesDtoesTable.generalExpenses")
    public void onCategoryGeneralExpensesDtoesTableGeneralExpenses(Action.ActionPerformedEvent event) {
        setGeneralExpenses();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        setGeneralExpenses();
    }

    private void setGeneralExpenses(){
        categoryGeneralExpensesDtoesDc.getMutableItems().clear();
        List<OperationCategory> categories =
                dataManager.load(OperationCategory.class).all().list();
        categoryGeneralExpensesDtoesDc.getMutableItems().addAll(initedCategoryExpensesDtoes(categories));
        categoryGeneralExpensesDtoesTable.sort(categoryGeneralExpensesDtoesTable.getColumns().get(0).getStringId(), Table.SortDirection.ASCENDING);
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


    @Subscribe("categoryGeneralExpensesDtoesTable")
    public void onCategoryGeneralExpensesDtoesTableSelection(Table.SelectionEvent<CategoryExpensesDto> event) {
        Optional<CategoryExpensesDto> optionalCategoryExpensesDto = event.getSelected().stream().findFirst();
        optionalCategoryExpensesDto.ifPresent(
                categoryExpensesDto -> selectedCategoryExpensesDto = categoryExpensesDto
        );
    }
    @Subscribe("categoryGeneralExpensesDtoesTable.periodEdit")
    public void onCategoryGeneralExpensesDtoesTablePeriodEdit(Action.ActionPerformedEvent event) {

        CategoryExpensesByPeriodDto byPeriodDto = dataManager.create(CategoryExpensesByPeriodDto.class);
        byPeriodDto.setCategory(selectedCategoryExpensesDto.getCategory());

        editScreen = screens.create(CategoryExpensesByPeriodDtoEdit.class);
        editScreen.setEntityToEdit(byPeriodDto);
        editScreen.addAfterCloseListener(
                listener -> setExpensesByPeriodInDataContainer(editScreen.getCategoryExpensesByPeriodDto())
        );
        editScreen.show();
    }
    private void setExpensesByPeriodInDataContainer(CategoryExpensesByPeriodDto expensesByPeriod){
        selectedCategoryExpensesDto.setExpenses(expensesByPeriod.getExpenses());
        categoryGeneralExpensesDtoesDc.replaceItem(selectedCategoryExpensesDto);
    }
    
}