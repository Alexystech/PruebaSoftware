package com.welldex.PruebaSoftware.service;

import com.welldex.PruebaSoftware.entity.CSuelta;

import java.util.List;

public interface CSueltaService {
    CSuelta createCSuelta(CSuelta cSuelta);
    void deleteCSueltaById(long id);
    CSuelta updateCSuelta(CSuelta cSuelta);
    CSuelta getCSueltaById(long id);
    List<CSuelta> getAllCargasSuelta();
    Boolean descargaCargaSuelta(long id, double canDescargada);
}
