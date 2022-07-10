package com.company.firstjmixproject.app.exception.handler;

import com.company.firstjmixproject.app.exception.FundsValueException;
import io.jmix.ui.exception.AbstractUiExceptionHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;

@Component("ui_FundsValueExceptionHandler")
public class FundsValueExceptionUIHandler extends AbstractUiExceptionHandler {

    public FundsValueExceptionUIHandler(){
        super(FundsValueException.class.getName());
    }

    @Override
    protected void doHandle(String className,
                            String message,
                            @Nullable Throwable throwable,
                            UiContext context) {
        context.getDialogs().createMessageDialog().withMessage(message).closeOnClickOutside().show();
    }
}
