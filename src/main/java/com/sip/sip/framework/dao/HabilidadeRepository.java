package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Habilidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HabilidadeRepository extends CrudRepository<Habilidade, Long> {
    List<Habilidade> findByAtivoEquals(Boolean ativo);
}
