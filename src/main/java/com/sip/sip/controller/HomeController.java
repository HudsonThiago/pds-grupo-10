package com.sip.sip.controller;

import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ManterUsuarioService manterUsuarioService;

    @RequestMapping(value = "cadastro", method = RequestMethod.GET)
    public String cadastroUsuario() {
        return "home/cadastro";
    }

    @RequestMapping(value = "cadastro", method = RequestMethod.POST)
    public String cadastroUsuario(UsuarioCadastroDTO usuario, RedirectAttributes attributes) {
        return manterUsuarioService.criarUsuario(usuario, attributes);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginUsuario() {
        return "home/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginUsuario(Usuario usuario, RedirectAttributes attributes) {

        return manterUsuarioService.login(attributes, usuario.getEmail(),usuario.getSenha());
    }
}