package com.sip.sip.dao;

import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(Long id);

    Usuario criarUsuario(Usuario usuario);
    Usuario atualizarUsuario(Usuario usuario);
    boolean Login(String email, String senha);
}
