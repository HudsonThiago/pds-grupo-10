package com.sip.sip.controller;

import com.sip.sip.dto.AtualizarUsuarioDTO;
import com.sip.sip.exception.HabilidadeNotFoundException;
import com.sip.sip.exception.Usuario.UsuarioAlreadyExistsException;
import com.sip.sip.exception.Usuario.UsuarioException;
import com.sip.sip.exception.Usuario.UsuarioNotFoundException;
import com.sip.sip.exception.Usuario.UsuarioUnsupportedPasswordsException;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Habilidade;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ICargoService;
import com.sip.sip.service.IHabilidadeService;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ManterUsuarioController {

    @Autowired
    private ManterUsuarioService usuarioService;
    @Autowired
    private IHabilidadeService habilidadeService;
    @Autowired
    private ICargoService cargoService;

    @GetMapping("/listar-usuarios")
    public String listarUsuario(Model model) {

        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);


        return "listar-usuarios";
    }

    @RequestMapping(value = "/editar-usuario/{id}", method = RequestMethod.GET)
    public String editarUsuario(Model model, @PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuario);
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);


        return "editar-usuario";
    }

    @PostMapping(value = "/editar-usuario/{id}")
    public String editarUsuario(@PathVariable Long id, AtualizarUsuarioDTO usuarioDTO) throws Exception {

        try{
            Usuario usuario = usuarioService.atualizarUsuario(id, usuarioDTO);
            return "redirect:/listar-usuarios";
        }catch(UsuarioNotFoundException e) {
            return "redirect:/editar-usuario/"+id;
        }catch (HabilidadeNotFoundException e){
            return "redirect:/editar-usuario/"+id;
        }
    }

    @RequestMapping(value = "/excluir-usuario/{id}", method = RequestMethod.POST)
    public String excluirUsuario(@PathVariable Long id) {

        usuarioService.excluirUsuario(id);

        return "redirect:/listar-usuarios";

    }

}
