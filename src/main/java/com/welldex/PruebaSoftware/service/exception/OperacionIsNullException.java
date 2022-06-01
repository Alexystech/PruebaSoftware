package com.welldex.PruebaSoftware.service.exception;

public class OperacionIsNullException extends RuntimeException {

    public static OperacionIsNullException of() {
        return new OperacionIsNullException();
    }

    public OperacionIsNullException() {
        super("The object can't be null");
    }

}
