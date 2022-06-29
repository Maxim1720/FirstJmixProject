package com.company.firstjmixproject.screen.operation;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.Operation;

@UiController("idid_Operation.browse")
@UiDescriptor("operation-browse.xml")
@LookupComponent("operationsTable")
public class OperationBrowse extends StandardLookup<Operation> {
}