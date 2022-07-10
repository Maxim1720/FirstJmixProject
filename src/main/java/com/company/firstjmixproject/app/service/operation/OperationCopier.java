package com.company.firstjmixproject.app.service.operation;

import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("idid_OperationCopier")
public class OperationCopier {
    @Autowired
    private DataManager dataManager;

    public Operation copyFrom(Operation source){
        Operation target = dataManager.create(Operation.class);
        target.setIsCanceled(source.getIsCanceled());
        target.setComment(source.getComment());
        target.setCategory(source.getCategory());
        target.setBill(source.getBill());
        target.setSum(source.getSum());
        target.setType(source.getType());
        return target;
    }

}
