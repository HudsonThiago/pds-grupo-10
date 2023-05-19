package com.sip.sip.controller;

import com.sip.sip.dto.UsuarioCadastroDTO;
import com.sip.sip.exception.Usuario.*;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ManterUsuarioService manterUsuarioService;

    @RequestMapping(value = "cadastro", method = RequestMethod.GET)
    public String cadastroUsuario() {
        return "home/cadastro";
    }

    @RequestMapping(value = "cadastro", method = RequestMethod.POST)
    public String cadastroUsuario(UsuarioCadastroDTO usuario, RedirectAttributes attributes) throws UsuarioException {
        try{
            Usuario novoUsuario = manterUsuarioService.criarUsuario(usuario);
            return "redirect:/";
        }catch(UsuarioAlreadyExistsException e) {
            attributes.addFlashAttribute("emailError", "Este email já foi cadastrado, digite outro!");
        }catch(UsuarioUnsupportedPasswordsException e) {
            attributes.addFlashAttribute("senhaError", "As senhas não são iguais, tente novamente!");
        }
        return "redirect:/cadastro";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginUsuario() {
        return "home/index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String loginUsuario(Usuario usuario, RedirectAttributes attributes) throws UsuarioException {
        Usuario usuarioLogado = new Usuario();
        try{
            usuarioLogado = manterUsuarioService.login(usuario.getEmail(),usuario.getSenha());
            return "redirect:/explorar-projetos";
        }catch(UsuarioNotFoundException e) {
            attributes.addFlashAttribute("emailError", "Usuário com este email não foi cadastrado!");
        }catch(UsuarioIncorrectDataException e) {
            attributes.addFlashAttribute("senhaError", "Email ou senha incorretas, tente novamente!");
        }

        return "redirect:/";
    }
}