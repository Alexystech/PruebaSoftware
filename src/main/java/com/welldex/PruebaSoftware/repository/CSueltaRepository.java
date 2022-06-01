package com.welldex.PruebaSoftware.repository;

import com.welldex.PruebaSoftware.entity.CSuelta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CSueltaRepository extends CrudRepository<CSuelta, Long> {
}
