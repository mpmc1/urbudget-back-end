package com.urbudget.apitransaction.util;

public class CustomException extends RuntimeException{
    private final String message;
    private final Exception rootException;

    public CustomException(String message, Exception rootException) {
        super();
        this.message = message;
        this.rootException = rootException;
    }

    public CustomException(String message) {
        super();
        this.message = message;
        this.rootException = null;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public Exception getRootException() {
        return rootException;
    }
}
