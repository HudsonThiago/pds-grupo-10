package com.sip.sip.dao;

import com.sip.sip.model.Cidade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {
    List<Cidade> findByNomeLikeIgnoreCase(String nome);

    Cidade findByNome(String nome);
}
