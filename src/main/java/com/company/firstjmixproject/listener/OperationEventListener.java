package com.company.firstjmixproject.listener;

import com.company.firstjmixproject.app.exception.FundsValueException;
import com.company.firstjmixproject.app.service.operation.OperationExecutor;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.event.EntitySavingEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("idid_OperationEventListener")
public class OperationEventListener {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OperationEventListener.class);
    @Autowired
    OperationExecutor operationExecutor;

    @EventListener
    public void onOperationSaving(EntitySavingEvent<Operation> event) {

        try {
            operationExecutor.execute(event.getEntity());
        }
        catch (FundsValueException e){
            log.error(e.getMessage());
        }

    }
}