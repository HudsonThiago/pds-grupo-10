package com.sip.sip.controller;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.exception.HabilidadeNotFoundException;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Habilidade;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.HabilidadeService;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ManterHabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;


    @GetMapping("listar-habilidades")
    public String listarHabilidades(Model model) {
            List<Habilidade> habilidades = habilidadeService.listarTodasAsHabilidades();
            model.addAttribute("habilidades", habilidades);
        return "listar-habilidades";
    }


    @PostMapping("/excluir-habilidade/{id}")
    public String excluirHabilidade(@PathVariable Long id) {
        habilidadeService.excluirHabilidade(id);
        return "redirect:/listar-habilidades";
    }
    @PostMapping("/ativar-habilidade/{id}")
    public String mudarEstadoHabilidade(@PathVariable Long id) {
        habilidadeService.mudarEstadoHabilidade(id);
        return "redirect:/listar-habilidades";
    }

    @GetMapping("solicitar-habilidade")
    public String solicitarHabilidade() {
        return "solicitar-habilidade";
    }


    @PostMapping("solicitar-habilidade")
    public String solicitarHabilidade(Habilidade habilidade) {
        habilidadeService.solicitarHabilidade(habilidade);
        return "redirect:/listar-habilidades";
    }
}