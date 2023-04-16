package com.sip.sip.controller;

import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ManterUsuarioService manterUsuarioService;

    @RequestMapping(value="cadastro", method=RequestMethod.GET)
    public String cadastroUsuario() {
        return "home/cadastro";
    }

    @RequestMapping(value="cadastro", method=RequestMethod.POST)
    public String cadastroUsuario(UsuarioCadastroDTO usuario) {
        manterUsuarioService.criarUsuario(usuario);
        return "home/cadastro";
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String loginUsuario() {
        return "home/index";
    }
}
