package com.sip.sip.dao;

import com.sip.sip.model.Habilidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HabilidadeRepository extends CrudRepository<Habilidade, Long> {
    List<Habilidade> findByAtivoEquals(Boolean ativo);
}
