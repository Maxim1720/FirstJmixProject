package com.company.firstjmixproject.screen.categorygeneralexpensesdto;

import com.company.firstjmixproject.app.service.category.CategoryExpenseService;
import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.DataManager;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.firstjmixproject.app.dto.CategoryGeneralExpensesDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("idid_CategoryGeneralExpensesDto.browse")
@UiDescriptor("category-general-expenses-dto-browse.xml")
@LookupComponent("categoryGeneralExpensesDtoesTable")
public class CategoryGeneralExpensesDtoBrowse extends StandardLookup<CategoryGeneralExpensesDto> {


    @Autowired
    private CollectionContainer<CategoryGeneralExpensesDto> categoryGeneralExpensesDtoesDc;

    @Autowired
    DataManager dataManager;
    
    @Autowired
    CategoryExpenseService expenseService;
    
    @Subscribe
    public void onInit(InitEvent event) {


    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<OperationCategory> categories =
                dataManager.load(OperationCategory.class).all().list();

        for (OperationCategory category:
                categories) {

            CategoryGeneralExpensesDto generalExpensesDto =
                    dataManager.create(CategoryGeneralExpensesDto.class);

            generalExpensesDto.setCategory(category);
            generalExpensesDto.setExpenses(expenseService.all(category));

            categoryGeneralExpensesDtoesDc.getMutableItems().add(generalExpensesDto);
        }
    }
    
    
    
}