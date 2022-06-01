package com.welldex.PruebaSoftware.service.impl;

import com.welldex.PruebaSoftware.entity.Contenedor;
import com.welldex.PruebaSoftware.entity.Estatus;
import com.welldex.PruebaSoftware.entity.EstatusContenedor;
import com.welldex.PruebaSoftware.entity.TipoOperacion;
import com.welldex.PruebaSoftware.repository.ContenedorRepository;
import com.welldex.PruebaSoftware.service.ContenedorService;
import com.welldex.PruebaSoftware.service.exception.ContenedorFolioIsEmptyException;
import com.welldex.PruebaSoftware.service.exception.ContenedorIsNullException;
import com.welldex.PruebaSoftware.service.exception.ContenedorNotFoundException;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContenedorServiceImpl implements ContenedorService {

    private final ContenedorRepository contenedorRepository;

    @Override
    public Contenedor createContenedor(Contenedor contenedor) {

        if ( contenedor == null ) {
            throw ContenedorIsNullException.of();
        }

        if ( contenedor.getOperacion().getTipoOperacion().equals(TipoOperacion.IMPORTACION) ) {
            contenedor.getOperacion()
                    .setFechaArribo(Calendar.getInstance());

            contenedor.getOperacion().setEstatus(Estatus.ETA);
            contenedor.setEstatusContenedor(EstatusContenedor.EN_TRANSPORTE);
        }

        if ( contenedor.getOperacion().getTipoOperacion().equals(TipoOperacion.EXPORTACION) ) {
            contenedor.getOperacion()
                    .setFechaZarpe(Calendar.getInstance());

            contenedor.getOperacion().setEstatus(Estatus.ETD);
            contenedor.setEstatusContenedor(EstatusContenedor.EN_TRANSPORTE);
        }

        return contenedorRepository.save(contenedor);
    }

    @Override
    public void deleteContenedorByFolio(String folio) {

        if ( folio == null ) {
            throw ContenedorIsNullException.of();
        }

        if ( folio.equals("") ) {
            throw ContenedorFolioIsEmptyException.of();
        }

        Try.of( () -> getContenedorByFolio(folio) ).onFailure( (exception) -> {
            throw ContenedorNotFoundException.of(folio);
        });

        contenedorRepository.deleteById(folio);
    }

    @Override
    public Contenedor updateContenedor(Contenedor contenedor) {

        if ( contenedor == null ) {
            throw ContenedorIsNullException.of();
        }

        return contenedorRepository.save(contenedor);
    }

    @Override
    public Contenedor getContenedorByFolio(String folio) {

        if ( folio == null ) {
            throw ContenedorIsNullException.of();
        }

        if ( folio.equals("") ) {
            throw ContenedorFolioIsEmptyException.of();
        }

        return contenedorRepository.findById(folio)
                .orElseThrow( () -> ContenedorNotFoundException.of(folio) );
    }

    @Override
    public List<Contenedor> getAllContenedores() {
        return ((List<Contenedor>) contenedorRepository.findAll());
    }

    @Override
    public Boolean descargaContenedor(String folio) {

        Contenedor contenedor = getContenedorByFolio(folio);
        contenedor.setFechaDescargo(Calendar.getInstance());

        if ( contenedor.getOperacion().getEstatus().equals(Estatus.ETA) ||
            contenedor.getOperacion().getEstatus().equals(Estatus.ETD) ) {

            contenedor.getOperacion().setEstatus(Estatus.EN_DESCARGO);

        }

        if ( contenedor.getEstatusContenedor().equals(EstatusContenedor.EN_TRANSPORTE) ) {
            contenedor.setEstatusContenedor(EstatusContenedor.DESCARGADO);
        }

        contenedorRepository.save(contenedor);

        List<Contenedor> contenedores = getAllContenedores().stream()
                .filter( selftContenedor -> selftContenedor.getEstatusContenedor().equals(EstatusContenedor.EN_TRANSPORTE) &&
                        selftContenedor.getOperacion().getFolio().equals(contenedor.getOperacion().getFolio()) )
                .collect(Collectors.toList());

        if ( contenedores.isEmpty() ) {
            contenedor.getOperacion().setEstatus(Estatus.DESCARGADA);
        }

        contenedorRepository.save(contenedor);

        return true;
    }
}
