package com.sip.sip.dao;

import com.sip.sip.model.Projeto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long>{

}
