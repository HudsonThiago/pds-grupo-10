package com.sip.sip.service;

import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Usuario;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    public String criarUsuario(UsuarioCadastroDTO usuarioDto, RedirectAttributes attributes){
        boolean hasError = false;

        Usuario usuarioFiltrado = buscarUsuarioPorEmail(usuarioDto.getEmail());
        if(!isNull(usuarioFiltrado)){
            hasError = true;
            attributes.addFlashAttribute("emailError", "Este email já foi cadastrado, digite outro!");
        }

        if(!usuarioDto.getSenha().equals(usuarioDto.getConfirmarSenha())){
            hasError = true;
            attributes.addFlashAttribute("senhaError", "As senhas não são iguais, tente novamente!");
        }

        if(hasError == true){
            return "redirect:/cadastro";
        }


        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuarioDAOJPA.criarUsuario(usuario);
        return "redirect:/";
    }

    public void excluirUsuario(Long id){
        usuarioDAOJPA.excluirUsuario(id);
    }

    public Usuario atualizarUsuario(long id, AtualizarUsuarioDTO atualizarUsuarioDTO) throws ProjetoNotFoundException{
        Optional<Usuario> usuario = Optional.of(buscarUsuarioPorId(id));

        if (usuario.isEmpty()) {
            throw new ProjetoNotFoundException("Usuario não encontrado com id: " + id);
        }
        if(atualizarUsuarioDTO.getNome() != null) usuario.get().setNome(atualizarUsuarioDTO.getNome());
        if(atualizarUsuarioDTO.getEmail() != null) usuario.get().setEmail(atualizarUsuarioDTO.getEmail());
        if(atualizarUsuarioDTO.getDescricao() != null) usuario.get().setDescricao(atualizarUsuarioDTO.getDescricao());

        return usuarioDAOJPA.atualizarUsuario(usuario.get());
    }

    public String login(RedirectAttributes attributes, String email, String senha){
        Usuario usuario = usuarioDAOJPA.buscarUsuarioPorEmail(email);

        if(!isNull(usuario)){
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                UsuarioLogado usuarioLogado = new UsuarioLogado();
                usuarioLogado.id = usuario.getId();
                usuarioLogado.nome = usuario.getNome();
                return "redirect:/explorar-projetos";
            } else {
                attributes.addFlashAttribute("senhaError", "Email ou senha incorretas, tente novamente!");
            }
        }else {
            attributes.addFlashAttribute("emailError", "Usuário com este email não foi cadastrado!");
        }

        return "redirect:/";
    }
}
