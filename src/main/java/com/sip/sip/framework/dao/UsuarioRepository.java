package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {

    Usuario findByEmail(String email);
}
