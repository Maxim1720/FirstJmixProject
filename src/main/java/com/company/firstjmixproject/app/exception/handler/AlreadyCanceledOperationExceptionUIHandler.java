package com.company.firstjmixproject.app.exception.handler;

import com.company.firstjmixproject.app.exception.AlreadyCanceledOperationException;
import com.company.firstjmixproject.app.exception.FundsValueException;
import io.jmix.ui.exception.AbstractUiExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component("ui_AlreadyCanceledOperationExceptionHandler")
public class AlreadyCanceledOperationExceptionUIHandler extends AbstractUiExceptionHandler {

    public AlreadyCanceledOperationExceptionUIHandler(){
        super(AlreadyCanceledOperationException.class.getName());
    }

    @Override
    protected void doHandle(String className, String message, @Nullable Throwable throwable, UiContext context) {
        context.getDialogs().createMessageDialog().withMessage(message).closeOnClickOutside().show();
    }
}
