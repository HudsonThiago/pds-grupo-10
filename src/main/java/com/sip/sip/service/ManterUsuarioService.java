package com.sip.sip.service;

import com.sip.sip.dao.TecnologiaDAOJPA;
import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.TecnologiaNotFoundException;
import com.sip.sip.exception.Usuario.*;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ManterUsuarioService {

    @Autowired
    private UsuarioDAOJPA usuarioDAOJPA;

    @Autowired
    private TecnologiaDAOJPA tecnologiaDAOJPA;

    public List<Usuario> listarUsuarios(){
        return usuarioDAOJPA.listarUsuarios();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioDAOJPA.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioDAOJPA.buscarUsuarioPorEmail(email);
    }

    public Usuario criarUsuario(UsuarioCadastroDTO usuarioDto) throws UsuarioException{

        Usuario usuarioFiltrado = buscarUsuarioPorEmail(usuarioDto.getEmail());
        if(!isNull(usuarioFiltrado)){
            throw new UsuarioAlreadyExistsException("Usuário já existe");
        }

        if(!usuarioDto.getSenha().equals(usuarioDto.getConfirmarSenha())){
            throw new UsuarioUnsupportedPasswordsException("As senhas não são iguais");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return usuarioDAOJPA.criarUsuario(usuario);
    }

    public void excluirUsuario(Long id){
        usuarioDAOJPA.excluirUsuario(id);
    }

    public Usuario atualizarUsuario(long id, AtualizarUsuarioDTO atualizarUsuarioDTO) throws Exception {
        Optional<Usuario> usuario = Optional.of(buscarUsuarioPorId(id));

        if (usuario.isEmpty()) {
            throw new UsuarioNotFoundException("Nenhum usuário encontrado");
        }
        if(atualizarUsuarioDTO.getNome() != null) usuario.get().setNome(atualizarUsuarioDTO.getNome());
        if(atualizarUsuarioDTO.getEmail() != null) usuario.get().setEmail(atualizarUsuarioDTO.getEmail());
        if(atualizarUsuarioDTO.getDescricao() != null) usuario.get().setDescricao(atualizarUsuarioDTO.getDescricao());

        List<Tecnologia> tecnologias = new ArrayList<Tecnologia>();

        if(atualizarUsuarioDTO.getIdTecnologias().size() != 0){
            for (long idTecnologia:atualizarUsuarioDTO.getIdTecnologias()) {
                tecnologias.add(tecnologiaDAOJPA.buscarTecnologia(idTecnologia));
            }
        } else {
            throw new TecnologiaNotFoundException("Tecnologia não encontrada");
        }

        usuario.get().setTecnologias(tecnologias);

        return usuarioDAOJPA.atualizarUsuario(usuario.get());
    }

    public Usuario login(String email, String senha) throws UsuarioException {
        Usuario usuario = usuarioDAOJPA.buscarUsuarioPorEmail(email);

        if(!isNull(usuario)){
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                return usuario;
            } else {
                throw new UsuarioIncorrectDataException("email ou senha incorreta");
            }
        }else {
            throw new UsuarioNotFoundException("usuario não foi encontrado");
        }
    }
}
