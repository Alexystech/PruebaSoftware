package com.welldex.PruebaSoftware.service.exception;

public class CSueltaIsNullException extends RuntimeException {

    public static CSueltaIsNullException of() {
        return new CSueltaIsNullException();
    }

    public CSueltaIsNullException() {
        super("The object can't be null");
    }

}
