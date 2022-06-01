package com.welldex.PruebaSoftware.service.impl;

import com.welldex.PruebaSoftware.entity.CSuelta;
import com.welldex.PruebaSoftware.entity.Estatus;
import com.welldex.PruebaSoftware.entity.TipoOperacion;
import com.welldex.PruebaSoftware.repository.CSueltaRepository;
import com.welldex.PruebaSoftware.service.CSueltaService;
import com.welldex.PruebaSoftware.service.exception.CSueltaIsNullException;
import com.welldex.PruebaSoftware.service.exception.CSueltaNotFoundException;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CSueltaServiceImpl implements CSueltaService {

    private final CSueltaRepository cSueltaRepository;

    @Override
    public CSuelta createCSuelta(CSuelta cSuelta) {

        if (cSuelta == null ) {
            throw CSueltaIsNullException.of();
        }

        if ( cSuelta.getOperacion().getTipoOperacion().equals(TipoOperacion.IMPORTACION) ) {
            cSuelta.getOperacion()
                    .setFechaArribo(Calendar.getInstance());

            cSuelta.getOperacion().setEstatus(Estatus.ETA);
        }

        if ( cSuelta.getOperacion().getTipoOperacion().equals(TipoOperacion.EXPORTACION) ) {
            cSuelta.getOperacion()
                    .setFechaZarpe(Calendar.getInstance());

            cSuelta.getOperacion().setEstatus(Estatus.ETD);
        }

        return cSueltaRepository.save(cSuelta);
    }

    @Override
    public void deleteCSueltaById(long id) {

        if ( id < 1 ) {
            throw CSueltaIsNullException.of();
        }

        Try.of( () -> getCSueltaById(id) ).onFailure( (exception) -> {
            throw CSueltaNotFoundException.of(id);
        });

        cSueltaRepository.deleteById(id);
    }

    @Override
    public CSuelta updateCSuelta(CSuelta cSuelta) {

        if (cSuelta == null ) {
            throw CSueltaIsNullException.of();
        }

        return cSueltaRepository.save(cSuelta);
    }

    @Override
    public CSuelta getCSueltaById(long id) {
        return cSueltaRepository.findById(id)
                .orElseThrow( () -> CSueltaNotFoundException.of(id) );
    }

    @Override
    public List<CSuelta> getAllCargasSuelta() {
        return ((List<CSuelta>) cSueltaRepository.findAll());
    }

}
