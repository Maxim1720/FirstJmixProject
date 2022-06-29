package com.company.firstjmixproject.screen.operationtype;

import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.OperationType;

@UiController("idid_OperationType.browse")
@UiDescriptor("operation-type-browse.xml")
@LookupComponent("operationTypesTable")
public class OperationTypeBrowse extends StandardLookup<OperationType> {
}