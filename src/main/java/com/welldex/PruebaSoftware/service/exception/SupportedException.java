package com.welldex.PruebaSoftware.service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * En este enum se definen los diferentes Exceptions que
 * serán soportados en el backend, si el error es soportado
 * se retornara un error personalizado, en caso de que el error
 * no este definido en este enum, se dirá que es un error no
 * soportado y por lo tanto retornara un Internal Server Error
 * 500.
 *
 * @see com.welldex.PruebaSoftware.config.RestExceptionInterceptor
 */
@AllArgsConstructor
@Getter
public enum SupportedException {

    CONTENEDOR_FOLIO_IS_EMPTY(ContenedorFolioIsEmptyException.class, HttpStatus.NOT_ACCEPTABLE),
    CONTENEDOR_IS_NULL(ContenedorIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    CONTENEDOR_NOT_FOUND(ContenedorNotFoundException.class, HttpStatus.NOT_FOUND),
    CSUELTA_IS_NULL(CSueltaIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    CSUELTA_NOT_FOUND(CSueltaNotFoundException.class, HttpStatus.NOT_FOUND),
    OPERACION_IS_NULL(OperacionIsNullException.class, HttpStatus.UNPROCESSABLE_ENTITY),
    OPERACION_NOT_FOUND(OperacionNotFoundException.class, HttpStatus.NOT_FOUND);

    private final Class<? extends Throwable> exceptionClass;
    private final HttpStatus status;

}
