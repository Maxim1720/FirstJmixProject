package com.company.firstjmixproject.app.service.operation;

import com.company.firstjmixproject.app.exception.FundsValueException;
import com.company.firstjmixproject.entity.Bill;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service("idid_OperationExecutor")
public class OperationExecutor {
    private final DataManager dataManager;

    private BigDecimal funds;


    @Autowired
    public OperationExecutor(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void execute(Operation operation){
        Bill bill = operation.getBill();
        funds = bill.getFunds();
        setBillFundsFromOpType(operation);
        bill.setFunds(funds);
        dataManager.save(bill);
    }

    private void setBillFundsFromOpType(Operation operation){

        switch (operation.getType()) {
            case ENROLLMENT: {
                funds = funds.add(operation.getSum());
                break;
            }
            case WITHDRAWAL: {
                funds = funds.subtract(operation.getSum());
                break;
            }
        }

        if(funds.doubleValue() < 0){
            throw new FundsValueException();
        }
    }
}
