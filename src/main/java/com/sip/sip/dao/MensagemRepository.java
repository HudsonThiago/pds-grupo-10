package com.sip.sip.dao;

import com.sip.sip.model.Cargo;
import com.sip.sip.model.Mensagem;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends CrudRepository<Mensagem,Long>{
    List<Mensagem> findByUsuarioDestinatario(Usuario usuario);


}

