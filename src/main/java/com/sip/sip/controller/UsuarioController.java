package com.sip.sip.controller;

import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    final ManterUsuarioService usuarioService;

    public UsuarioController(ManterUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


}
