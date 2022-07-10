package com.company.firstjmixproject.app.exception;

public class AlreadyCanceledOperationException extends RuntimeException{
    public AlreadyCanceledOperationException(String msg){
        super(msg);
    }
}
