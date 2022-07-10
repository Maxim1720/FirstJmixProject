package com.company.firstjmixproject.listener;

import com.company.firstjmixproject.app.service.operation.OperationExecutor;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("idid_OperationEventListener")
public class OperationEventListener {
    @Autowired
    OperationExecutor operationExecutor;
    @Autowired
    private DataManager dataManager;
    @EventListener
    public void onOperationSaving(EntitySavingEvent<Operation> event) {
        if(dataManager.load(Operation.class).id(event.getEntity().getId()).optional().isEmpty()){
            operationExecutor.execute(event.getEntity());
        }
    }
}