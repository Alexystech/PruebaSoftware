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

    /**
     * Este método recibe el {@code id} y la {@code canDescargada} para
     * poder obtener el cargamento suelto al que se quiere modificar
     * los datos y por parte de la cantidad descargada, llevar la
     * contabilidad del cargamento (posiblemente granular o liquido) cuanto
     * se va descargando por evento.
     * Mientras la cantidad descargada sea menor a la cantidad registrada de
     * origen el estatus de la operación pasa a ser EN DESCARGO, pero cuando
     * la cantidad descargada sea igual a la cantidad de origen el estatus de la
     * operación pasa a ser DESCARGADA.
     * @param id
     * @param canDescargada
     * @return
     */
    @Override
    public Boolean descargaCargaSuelta(long id, double canDescargada) {

        CSuelta cSuelta = getCSueltaById(id);
        cSuelta.setFechaDesc(Calendar.getInstance());

        if ( cSuelta.getCantidadDesc() == null ) {
            cSuelta.setCantidadDesc(0.0);
        }

        cSuelta.setCantidadDesc(canDescargada + cSuelta.getCantidadDesc());

        if ( cSuelta.getCantidadDesc() < cSuelta.getCantidad() ) {
            cSuelta.getOperacion().setEstatus(Estatus.EN_DESCARGO);
        }

        if ( cSuelta.getCantidadDesc().doubleValue() == cSuelta.getCantidad().doubleValue() ) {
            cSuelta.getOperacion().setEstatus(Estatus.DESCARGADA);
        }

        cSueltaRepository.save(cSuelta);

        return true;
    }
}
