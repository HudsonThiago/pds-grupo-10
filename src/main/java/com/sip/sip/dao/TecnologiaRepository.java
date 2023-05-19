package com.sip.sip.dao;

import com.sip.sip.model.Tecnologia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TecnologiaRepository extends CrudRepository<Tecnologia, Long> {
    List<Tecnologia> findByAtivoEquals(Boolean ativo);
}
