package com.company.firstjmixproject.app.exception;

public class FundsValueException extends RuntimeException{
    public FundsValueException(){
        super("Funds value can't be a negative number");
    }
}
