package com.welldex.PruebaSoftware.service.impl;

import com.welldex.PruebaSoftware.entity.Operacion;
import com.welldex.PruebaSoftware.repository.OperacionRepository;
import com.welldex.PruebaSoftware.service.OperacionService;
import com.welldex.PruebaSoftware.service.exception.OperacionFolioIsEmptyException;
import com.welldex.PruebaSoftware.service.exception.OperacionIsNullException;
import com.welldex.PruebaSoftware.service.exception.OperacionNotFoundException;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OperacionServiceImpl implements OperacionService {

    private final OperacionRepository operacionRepository;

    @Override
    public Operacion createOperacion(Operacion operacion) {

        if ( operacion == null) {
            throw OperacionIsNullException.of();
        }

        return operacionRepository.save(operacion);
    }

    @Override
    public void deleteOperacionByFolio(String folio) {

        if ( folio == null ) {
            throw OperacionIsNullException.of();
        }

        if ( folio.equals("") ) {
            throw OperacionFolioIsEmptyException.of();
        }

        Try.of( () -> getOperacionByFolio(folio) ).onFailure( (exception) -> {
            throw OperacionNotFoundException.of(folio);
        });

        operacionRepository.deleteById(folio);
    }

    @Override
    public Operacion updateOperacion(Operacion operacion) {

        if ( operacion == null) {
            throw OperacionIsNullException.of();
        }

        return operacionRepository.save(operacion);
    }

    @Override
    public Operacion getOperacionByFolio(String folio) {

        if ( folio == null ) {
            throw OperacionIsNullException.of();
        }

        if ( folio.equals("") ) {
            throw OperacionFolioIsEmptyException.of();
        }

        return operacionRepository.findById(folio)
                .orElseThrow( () -> OperacionNotFoundException.of(folio) );
    }

    @Override
    public List<Operacion> getAllOperaciones() {
        return ((List<Operacion>) operacionRepository.findAll());
    }

}
