package com.company.firstjmixproject.app.service.funds;

import com.company.firstjmixproject.app.exception.FundsValueException;
import com.company.firstjmixproject.entity.Bill;
import com.company.firstjmixproject.entity.Operation;
import io.jmix.core.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("fundsCalculator")
public class FundsCalculator {
    @Autowired
    Messages messages;
    public BigDecimal calculate(Operation operation, BigDecimal funds){

        switch (operation.getType()) {
            case ENROLLMENT: {
                return funds.add(operation.getSum());
            }
            case WITHDRAWAL: {
                funds = funds.subtract(operation.getSum());
                if(funds.doubleValue() < 0){
                    throw new FundsValueException(
                            messages.getMessage("com.company.firstjmixproject.entity/Bill.funds.validation.DecimalMin")
                    );
                }
                return funds;
            }
            default:
                return new BigDecimal(0);
        }


    }

}
