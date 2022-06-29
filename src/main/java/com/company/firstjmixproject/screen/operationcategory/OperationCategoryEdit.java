package com.company.firstjmixproject.screen.operationcategory;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.OperationCategory;

@UiController("idid_OperationCategory.edit")
@UiDescriptor("operation-category-edit.xml")
@EditedEntityContainer("operationCategoryDc")
public class OperationCategoryEdit extends StandardEditor<OperationCategory> {
}