package com.welldex.PruebaSoftware.repository;

import com.welldex.PruebaSoftware.entity.Contenedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenedorRepository extends CrudRepository<Contenedor, String> {
}
