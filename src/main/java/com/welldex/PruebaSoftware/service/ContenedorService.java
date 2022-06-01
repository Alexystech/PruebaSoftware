package com.welldex.PruebaSoftware.service;

import com.welldex.PruebaSoftware.entity.Contenedor;

import java.util.List;

public interface ContenedorService {
    Contenedor createContenedor(Contenedor contenedor);
    void deleteContenedorByFolio(String folio);
    Contenedor updateContenedor(Contenedor contenedor);
    Contenedor getContenedorByFolio(String folio);
    List<Contenedor> getAllContenedores();
}
