package com.sip.sip.config.controller;

import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.*;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    final ManterUsuarioService usuarioService;

    public UsuarioController(ManterUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public ResponseEntity<List<Usuario>> listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorID(@PathVariable(value = "id") long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable(value = "email") String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable(value = "id") long id, @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO) throws ProjetoNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarUsuario(id, atualizarUsuarioDTO));
    }
}
