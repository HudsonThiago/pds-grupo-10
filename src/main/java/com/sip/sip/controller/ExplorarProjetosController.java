package com.sip.sip.controller;

import com.sip.sip.model.Projeto;
import com.sip.sip.service.IExplorarProjetosService;
import com.sip.sip.service.IManterProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/explorar-projetos")
public class ExplorarProjetosController {
    @Autowired
    private IExplorarProjetosService explorarProjetosService;
    @GetMapping("")
    public String showProjetos(Model model) {
        List<Projeto> projetos = explorarProjetosService.listarProjetos();
        model.addAttribute("projetos", projetos);
        return "explorar-projetos";
    }

    @PostMapping("")
    public String filtrarProjetos(Model model, @RequestParam(name = "minCurtidas", required = false) Integer minCurtidas) {
        List<Projeto> projetos;
        if (minCurtidas != null) {
            projetos = explorarProjetosService.filtrarProjetosNumCurtidasMaior(minCurtidas.intValue());
        } else {
            projetos = explorarProjetosService.listarProjetos();
        }
        model.addAttribute("projetos", projetos);
        return "explorar-projetos";
    }
}
