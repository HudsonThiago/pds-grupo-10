package com.sip.sip.framework.controller;

import com.sip.sip.framework.dto.AtualizarUsuarioDTO;
import com.sip.sip.framework.exception.HabilidadeNotFoundException;
import com.sip.sip.framework.exception.Usuario.UsuarioNotFoundException;
import com.sip.sip.framework.model.Cargo;
import com.sip.sip.framework.model.Habilidade;
import com.sip.sip.framework.model.Usuario;
import com.sip.sip.framework.service.ICargoService;
import com.sip.sip.framework.service.IHabilidadeService;
import com.sip.sip.framework.service.ManterUsuarioService;
import com.sip.sip.framework.service.RecStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ManterUsuarioController {

    @Autowired
    private ManterUsuarioService usuarioService;
    @Autowired
    private IHabilidadeService habilidadeService;
    @Autowired
    private ICargoService cargoService;
    @Autowired
    private RecStrategy recStrategy;

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
        model.addAttribute("locationRequired", recStrategy.getLocationRequired());

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
