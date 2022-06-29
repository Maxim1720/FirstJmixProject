package com.company.firstjmixproject.screen.operationcategory;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.OperationCategory;

@UiController("idid_OperationCategory.browse")
@UiDescriptor("operation-category-browse.xml")
@LookupComponent("operationCategoriesTable")
public class OperationCategoryBrowse extends StandardLookup<OperationCategory> {
}