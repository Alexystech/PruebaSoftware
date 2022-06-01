package com.welldex.PruebaSoftware.config;

import com.google.common.collect.ImmutableMap;
import com.welldex.PruebaSoftware.service.exception.SupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Esta clase cumple con la función de ser un interceptor de todos los
 * errores que ocurren en el servidor, a sí mismo, se les dara un
 * formato para ser mostrado. Esto se hace con la finalidad de
 * hacer que nuestra aplicación no solo funcione sino que también
 * sepa como fallar ante diferentes situaciones.
 *
 * @see SupportedException
 * @see {link https://www.youtube.com/watch?v=XlWwthKuXrQ}
 */
@ControllerAdvice(annotations = RestController.class)
public class RestExceptionInterceptor {

    /**
     * En este método se recive un {@link Throwable} y se retorna
     * un {@link ResponseEntity} en el cual se determinara si el error que
     * nos están mandando es soportado o no en nuestro backend, en caso de
     * ser soportado se retorna el {@code supportedException} construido, en
     * caso contrario se construye un nuevo {@code ResponseEntity} pero
     * con un {@link HttpStatus} de tipo {@code INTERNAL_SERVER_ERROR}, lo que
     * significa que el error no está soportado y se retornara un 500.
     * @param throwable
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<Map<Object, Object>> processSupportedExceptions(Throwable throwable) {
        Optional<ResponseEntity<Map<Object, Object>>> supportedException = Arrays.stream(SupportedException.values())
                .filter(exception -> hasSameClass(exception, throwable))
                .map(this::createResponseEntityFromException)
                .findFirst();

        return supportedException.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private Boolean hasSameClass(SupportedException exception, Throwable throwable) {
        return throwable.getClass().equals(exception.getExceptionClass());
    }

    /**
     * Retorna un ResponseEntity y recibe un SupportedException el cual es interpretado
     * y formateado según el Exception obtenido.
     * @param exception
     * @return
     */
    private ResponseEntity<Map<Object, Object>> createResponseEntityFromException(SupportedException exception) {
        Objects.requireNonNull(exception);
        return new ResponseEntity<>(ImmutableMap.builder()
                .put("status", exception.getStatus().value())
                .put("error", exception.getStatus().getReasonPhrase())
                .build(),
                exception.getStatus());
    }

}
