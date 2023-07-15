package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.MensagemPrivada;
import com.sip.sip.framework.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemPRepository extends CrudRepository<MensagemPrivada,Long>{
    List<MensagemPrivada> findByUsuarioDestinatario(Usuario usuario);

    List<MensagemPrivada> findByUsuarioDestinatarioIdOrUsuarioRemetenteId(Long id1, Long id2);


}

