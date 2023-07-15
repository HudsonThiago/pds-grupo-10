package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("UsuarioDAOJPA")
public class UsuarioDAOJPA implements UsuarioDAO{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepository.findAll()
                .forEach(usuarios::add);
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario;
    }

    @Override
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    @Override
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
