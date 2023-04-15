package com.sip.sip.dao;

import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        return new Usuario();
    }
    @Override
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    @Override
    public boolean Login(String email, String senha){
        return false;
    }
}
