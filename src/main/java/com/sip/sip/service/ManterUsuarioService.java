package com.sip.sip.service;

import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
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

        return usuarioDAOJPA.criarUsuario(usuario);
    }

    public Usuario atualizarUsuario(long id, AtualizarUsuarioDTO atualizarUsuarioDTO) throws ProjetoNotFoundException{
        Usuario usuario = buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new ProjetoNotFoundException("Usuario não encontrado com id: " + id);
        }
        if(atualizarUsuarioDTO.getNome() != null) usuario.setNome(atualizarUsuarioDTO.getNome());
        if(atualizarUsuarioDTO.getEmail() != null) usuario.setEmail(atualizarUsuarioDTO.getEmail());
        if(atualizarUsuarioDTO.getDescricao() != null) usuario.setDescricao(atualizarUsuarioDTO.getDescricao());
        if(atualizarUsuarioDTO.getSenha() != null) usuario.setSenha(atualizarUsuarioDTO.getSenha());
        usuario.setTecnologias(atualizarUsuarioDTO.getTecnologias());

        return usuarioDAOJPA.atualizarUsuario(usuario);
    }



    public boolean Login(String email, String senha){
        return usuarioDAOJPA.Login(email, senha);
    }
}
