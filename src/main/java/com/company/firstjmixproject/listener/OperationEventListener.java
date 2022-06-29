package com.company.firstjmixproject.listener;

import com.company.firstjmixproject.entity.Bill;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("idid_OperationEventListener")
public class OperationEventListener {
    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onOperationSaving(EntitySavingEvent<Operation> event) {
        Bill bill = dataManager.load(Bill.class).id(event.getEntity().getBill().getId()).one();
        BigDecimal funds = event.getEntity().getBill().getFunds();
        bill.setFunds(funds.subtract(event.getEntity().getSum()));
        dataManager.save(bill);
    }
}