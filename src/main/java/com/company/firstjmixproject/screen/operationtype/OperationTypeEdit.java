package com.company.firstjmixproject.screen.operationtype;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.OperationType;

@UiController("idid_OperationType.edit")
@UiDescriptor("operation-type-edit.xml")
@EditedEntityContainer("operationTypeDc")
public class OperationTypeEdit extends StandardEditor<OperationType> {
}