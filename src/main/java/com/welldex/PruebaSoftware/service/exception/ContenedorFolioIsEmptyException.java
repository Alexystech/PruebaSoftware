package com.welldex.PruebaSoftware.service.exception;

public class ContenedorFolioIsEmptyException extends RuntimeException {

    public static ContenedorFolioIsEmptyException of() {
        return new ContenedorFolioIsEmptyException();
    }

    public ContenedorFolioIsEmptyException() {
        super("Contenedor folio is empty");
    }

}
