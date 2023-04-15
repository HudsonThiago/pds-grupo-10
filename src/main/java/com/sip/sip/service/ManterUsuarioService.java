package com.sip.sip.service;

import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.model.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManterUsuarioService {

    @Autowired
    private UsuarioDAOJPA usuarioDAOJPA;

    public List<Usuario> listarUsuarios(){
        return usuarioDAOJPA.listarUsuarios();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioDAOJPA.buscarUsuarioPorId(id);
    }

    public Usuario criarUsuario(UsuarioCadastroDTO usuarioDto){

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);

        String senha = usuario.getSenha();


        return usuarioDAOJPA.criarUsuario(usuario);
    }

    public boolean Login(String email, String senha){
        return usuarioDAOJPA.Login(email, senha);
    }
}
