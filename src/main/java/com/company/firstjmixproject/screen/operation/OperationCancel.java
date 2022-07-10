package com.company.firstjmixproject.screen.operation;


import com.company.firstjmixproject.app.service.operation.OperationCanceler;
import com.company.firstjmixproject.app.service.operation.OperationCopier;
import com.company.firstjmixproject.entity.Operation;
import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import io.jmix.core.SaveContext;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@UiController("idid_Operation.cancel")
@UiDescriptor("operation-edit.xml")
@EditedEntityContainer("operationDc")
public class OperationCancel extends OperationEdit{
    private Operation rolledBackOperation;
    @Autowired
    private OperationCanceler operationCanceler;
    @Autowired
    private EntityPicker<OperationCategory> categoryField;
    @Autowired
    private DataManager dataManager;

    @Override
    public void onBeforeShow(BeforeShowEvent event) {
        setSecureFieldsEditable(false);
        categoryField.setEditable(false);
        rolledBackOperation = getEditedEntity();
    }
    private void setNewEntityToEdit() {
        setEntityToEdit(operationCanceler
                .getPreparedToRollBackOperation(getEditedEntity()));
        setupEntityToEdit();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        rolledBackOperation.setIsCanceled(true);
        dataManager.save(rolledBackOperation);
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        setNewEntityToEdit();
    }
}
