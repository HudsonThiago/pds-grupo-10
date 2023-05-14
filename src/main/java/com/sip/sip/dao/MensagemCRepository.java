package com.sip.sip.dao;

import com.sip.sip.model.MensagemChat;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemCRepository extends CrudRepository<MensagemChat,Long>{
    List<MensagemChat> findByProjetoDestinatario(Projeto projeto);


}

