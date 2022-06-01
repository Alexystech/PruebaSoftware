package com.welldex.PruebaSoftware.service.exception;

public class CSueltaNotFoundException extends RuntimeException {

    public static CSueltaNotFoundException of(long id) {
        return new CSueltaNotFoundException(id);
    }

    public CSueltaNotFoundException(long id) {
        super(String.format("The 'carga suelta' that you try to get with id %d was not found",
                id));
    }

}
