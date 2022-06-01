package com.welldex.PruebaSoftware.service.exception;

public class OperacionFolioIsEmptyException extends RuntimeException {

    public static OperacionFolioIsEmptyException of() {
        return new OperacionFolioIsEmptyException();
    }

    public OperacionFolioIsEmptyException() {
        super("Operacion folio is empty");
    }

}
