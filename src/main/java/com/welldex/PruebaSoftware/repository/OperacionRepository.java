package com.welldex.PruebaSoftware.repository;

import com.welldex.PruebaSoftware.entity.Operacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperacionRepository extends CrudRepository<Operacion, String> {
}
