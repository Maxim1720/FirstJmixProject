package com.company.firstjmixproject.app.service.category;

import com.company.firstjmixproject.app.dto.CategoryExpensesByPeriodDto;
import com.company.firstjmixproject.entity.Operation;
import com.company.firstjmixproject.entity.OperationCategory;
import io.jmix.core.DataManager;
import io.jmix.core.querycondition.Condition;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service("CategorySpent")
public class CategoryExpenseCalculator implements CategoryExpenseService {

    @Autowired
    private DataManager dataManager;

    @Override
    public BigDecimal byPeriod(CategoryExpensesByPeriodDto ce) {
        Condition startIsBiggerOrEqual = PropertyCondition.greaterOrEqual("createdDate", ce.getStart());
        Condition endIsSmallOrEqual = PropertyCondition.lessOrEqual("createdDate", ce.getEnd());
        Condition nameEqual = PropertyCondition.equal("category", ce.getCategory());
        return expenseFromOperations(operationsFromConditions(startIsBiggerOrEqual,endIsSmallOrEqual,nameEqual));
    }

    @Override
    public BigDecimal all(OperationCategory operationCategory) {
        Condition categoryEqualsCondition = PropertyCondition.equal("category", operationCategory);
        return expenseFromOperations(operationsFromConditions(categoryEqualsCondition));
    }

    private List<Operation> operationsFromConditions(Condition ... conditions){
        return dataManager.load(Operation.class)
                .condition(LogicalCondition.and(conditions))
                .list();
    }

    private BigDecimal expenseFromOperations(List<Operation> operations){
        BigDecimal expense = new BigDecimal(0);
        for (Operation op :
                operations) {
            expense = expense.add(op.getSum());
        }
        return expense;
    }

}