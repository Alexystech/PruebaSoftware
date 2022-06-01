package com.welldex.PruebaSoftware.service.exception;

public class ContenedorNotFoundException extends RuntimeException {

    public static ContenedorNotFoundException of(String folio) {
        return new ContenedorNotFoundException(folio);
    }

    public ContenedorNotFoundException(String folio) {
        super(String.format("The contenedor that you try to get with folio %s was not found",
                folio));
    }

}
