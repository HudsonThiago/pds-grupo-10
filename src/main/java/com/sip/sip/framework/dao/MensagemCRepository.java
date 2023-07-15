package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.MensagemChat;
import com.sip.sip.framework.model.Projeto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemCRepository extends CrudRepository<MensagemChat,Long>{
    List<MensagemChat> findByProjetoDestinatario(Projeto projeto);


}

