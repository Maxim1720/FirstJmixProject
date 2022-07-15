package com.company.firstjmixproject.screen.operation;

import com.company.firstjmixproject.entity.Operation;
import io.jmix.ui.Screens;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Table;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@UiController("idid_Operation.browse")
@UiDescriptor("operation-browse.xml")
@LookupComponent("operationsTable")
public class OperationBrowse extends StandardLookup<Operation> {

    private Operation selectedOperation;
    @Autowired
    private Screens screens;
    @Autowired
    private Button cancelOperation;
    @Autowired
    private CollectionLoader<Operation> operationsDl;

    @Subscribe("cancelOperation")
    public void onCancelOperationClick(Button.ClickEvent event) {
        openCancelEditScreen();
    }

    private void openCancelEditScreen(){
        OperationCancel cancelScreen = screens.create(OperationCancel.class);
        cancelScreen.setEntityToEdit(selectedOperation);
        cancelScreen.show().addAfterCloseListener(
                l-> operationsDl.load()
        );
    }

    @Subscribe("operationsTable")
    public void onOperationsTableSelection(Table.SelectionEvent<Operation> event) {
        Optional<Operation> optionalOperation = event.getSelected().stream().findFirst();
        optionalOperation.ifPresent((op) -> {
            selectedOperation = op;
            cancelOperation.setEnabled(!selectedOperation.getIsCanceled());
        });
    }
}