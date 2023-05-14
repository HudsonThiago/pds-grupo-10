package com.sip.sip.dao;

import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemPRepository extends CrudRepository<MensagemPrivada,Long>{
    List<MensagemPrivada> findByUsuarioDestinatario(Usuario usuario);

    List<MensagemPrivada> findByUsuarioDestinatarioIdOrUsuarioRemetenteId(Long id1, Long id2);


}

