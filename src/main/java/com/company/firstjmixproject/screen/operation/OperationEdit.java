package com.company.firstjmixproject.screen.operation;

import com.company.firstjmixproject.entity.Bill;
import com.company.firstjmixproject.entity.OperationType;
import io.jmix.core.EntityStates;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import com.company.firstjmixproject.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;

@UiController("idid_Operation.edit")
@UiDescriptor("operation-edit.xml")
@EditedEntityContainer("operationDc")
public class OperationEdit extends StandardEditor<Operation> {

    @Autowired
    EntityStates entityStates;
    @Autowired
    private EntityPicker<Bill> billField;
    @Autowired
    private ComboBox<OperationType> opTypeField;
    @Autowired
    private TextField<BigDecimal> sumField;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        setSecureFieldsEditable(entityStates.isNew(getEditedEntity()));
    }

    protected void setSecureFieldsEditable(boolean editable){
        typeField.setEditable(editable);
        billField.setEditable(editable);
        sumField.setEditable(editable);
    }

    @Autowired
    private InstanceContainer<Operation> operationDc;
}