package com.sip.sip.service;

import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

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

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioDAOJPA.buscarUsuarioPorEmail(email);
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

    public String login(String email, String senha){
        Usuario usuario = usuarioDAOJPA.buscarUsuarioPorEmail(email);

        if(!isNull(usuario)){
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                return "redirect:/dashboard";
            }
        }
        return "redirect:/";
    }
    public void favoritarProjeto(Projeto p, Long usuarioId) {
        Usuario u = buscarUsuarioPorId(usuarioId);
        List<Projeto> projetosFavoritados = u.getProjetosFavoritados();
        projetosFavoritados.add(p);
        u.setProjetosFavoritados(projetosFavoritados);
        usuarioDAOJPA.atualizarUsuario(u);
    }

    public void curtirProjeto(Projeto projeto, Usuario usuario) {
        if (usuario.getProjetosCurtidos().contains(projeto)) {
            return;
        }
        usuario.getProjetosCurtidos().add(projeto);

        usuarioDAOJPA.atualizarUsuario(usuario);
    }
}
