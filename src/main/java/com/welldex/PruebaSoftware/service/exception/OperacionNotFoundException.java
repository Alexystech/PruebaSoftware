package com.welldex.PruebaSoftware.service.exception;

public class OperacionNotFoundException extends RuntimeException {

    public static OperacionNotFoundException of(String folio) {
        return new OperacionNotFoundException(folio);
    }

    public OperacionNotFoundException(String folio) {
        super(String.format("The 'operacion' that you try to get with folio %s was not found",
                folio));
    }

}
