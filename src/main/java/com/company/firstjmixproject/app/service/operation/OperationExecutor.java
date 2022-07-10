package com.company.firstjmixproject.app.service.operation;

import com.company.firstjmixproject.app.service.funds.FundsCalculator;
import com.company.firstjmixproject.entity.Bill;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service("idid_OperationExecutor")
public class OperationExecutor {
    private final DataManager dataManager;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private FundsCalculator fundsCalculator;
    private BigDecimal funds;


    @Autowired
    public OperationExecutor(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public void execute(Operation operation){
        Bill bill = operation.getBill();
        funds = bill.getFunds();
        setFundsFromOpType(operation);
        bill.setFunds(funds);
        dataManager.save(bill);
    }

    private void setFundsFromOpType(Operation operation){
        funds = fundsCalculator.calculate(operation,funds);
    }
}
