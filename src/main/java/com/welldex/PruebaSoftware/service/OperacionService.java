package com.welldex.PruebaSoftware.service;

import com.welldex.PruebaSoftware.entity.Operacion;

import java.util.List;

public interface OperacionService {
    Operacion createOperacion(Operacion operacion);
    void deleteOperacionByFolio(String folio);
    Operacion updateOperacion(Operacion operacion);
    Operacion getOperacionByFolio(String folio);
    List<Operacion> getAllOperaciones();
}
