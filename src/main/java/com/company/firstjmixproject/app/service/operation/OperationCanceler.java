package com.company.firstjmixproject.app.service.operation;

import com.company.firstjmixproject.app.exception.AlreadyCanceledOperationException;
import com.company.firstjmixproject.entity.Operation;
import com.company.firstjmixproject.entity.OperationType;
import io.jmix.core.DataManager;
import io.jmix.core.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;

@Transactional
@Component("idid_OperationCanceler")
public class OperationCanceler {

    @Autowired
    DataManager dataManager;

    @Autowired
    private Messages messages;
    @Autowired
    private OperationCopier operationCopier;

    public Operation getPreparedToRollBackOperation(Operation rolledBackOperation){
        checkIsCanceled(rolledBackOperation);

        Operation op = operationCopier.copyFrom(rolledBackOperation);
        op.setComment(messages
                .getMessage("com.company.firstjmixproject.entity/Operation.comment.rollback")
                +" "
                + DateFormat.getDateTimeInstance()
                        .format(rolledBackOperation.getCreatedDate()));

        op.setType(getCancelOpType(rolledBackOperation.getType()));
        op.setIsCanceled(true);
        return op;
    }


    public void rollback(Operation operation){
        Operation newOp = getPreparedToRollBackOperation(operation);
        newOp.setIsCanceled(true);
        dataManager.save(newOp);
    }


    private void checkIsCanceled(Operation operation){
        if(operation.getIsCanceled()){
            throw new AlreadyCanceledOperationException(
                    messages.getMessage("com.company.firstjmixproject.entity/Operation.canceled.alreadyCanceledException")
            );
        }
    }

    private OperationType getCancelOpType(OperationType operationType){
        if(operationType.equals(OperationType.ENROLLMENT)){
            return OperationType.WITHDRAWAL;
        }
        return OperationType.ENROLLMENT;
    }
}