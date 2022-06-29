package com.company.firstjmixproject.screen.operation;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.Operation;

@UiController("idid_Operation.edit")
@UiDescriptor("operation-edit.xml")
@EditedEntityContainer("operationDc")
public class OperationEdit extends StandardEditor<Operation> {
}