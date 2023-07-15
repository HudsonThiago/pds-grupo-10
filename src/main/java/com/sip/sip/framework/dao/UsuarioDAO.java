package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Usuario;

import java.util.List;

public interface UsuarioDAO {
    List<Usuario> listarUsuarios();

    Usuario buscarUsuarioPorId(Long id);
    Usuario buscarUsuarioPorEmail(String email);

    Usuario criarUsuario(Usuario usuario);
    Usuario atualizarUsuario(Usuario usuario);
    void excluirUsuario(Long id);
}
