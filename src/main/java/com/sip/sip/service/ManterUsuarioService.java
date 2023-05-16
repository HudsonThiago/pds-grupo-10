package com.sip.sip.service;

import com.sip.sip.config.JwtService;
import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.dto.AuthRequest;
import com.sip.sip.dto.AuthResponse;
import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Role;
import com.sip.sip.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class ManterUsuarioService {

    @Autowired
    private UsuarioDAOJPA usuarioDAOJPA;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public List<Usuario> listarUsuarios(){
        return usuarioDAOJPA.listarUsuarios();
    }

    public Usuario buscarUsuarioPorId(Long id){
        return usuarioDAOJPA.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioDAOJPA.buscarUsuarioPorEmail(email);
    }

    public AuthResponse criarUsuario(UsuarioCadastroDTO usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDto.getSenha()));
        usuario.setRole(Role.USER);
        usuarioDAOJPA.criarUsuario(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        return new AuthResponse(jwtToken);
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

    public AuthResponse authenticate(AuthRequest req) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                req.getEmail(),
                req.getSenha()
        ));
        var usuario = buscarUsuarioPorEmail(req.getEmail());
        var jwtToken = jwtService.generateToken(usuario);
        return new AuthResponse(jwtToken);
    }

    public Usuario getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return buscarUsuarioPorEmail(email);
    }
}
