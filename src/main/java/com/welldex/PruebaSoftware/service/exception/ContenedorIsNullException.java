package com.welldex.PruebaSoftware.service.exception;

public class ContenedorIsNullException extends RuntimeException {

    public static ContenedorIsNullException of() {
        return new ContenedorIsNullException();
    }

    public ContenedorIsNullException() {
        super("The object can't be null");
    }

}
