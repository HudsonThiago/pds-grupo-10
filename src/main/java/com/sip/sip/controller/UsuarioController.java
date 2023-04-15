package com.sip.sip.controller;

import com.sip.sip.model.*;
import com.sip.sip.service.ManterUsuarioService;
import com.sip.sip.dto.UsuarioCadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    final ManterUsuarioService usuarioService;

    public UsuarioController(ManterUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar-usuarios")
    public ResponseEntity<List<Usuario>> listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    /*
    @PostMapping("/criar-projeto")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(UsuarioCadastroDTO usuarioCadastroDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCadastroDTO.getNome());
        usuario.setEmail(usuarioCadastroDTO.getEmail());
        usuario.setSenha(usuarioCadastroDTO.getSenha());
        return usuarioService.criarUsuario(usuario);
    }*/


}
