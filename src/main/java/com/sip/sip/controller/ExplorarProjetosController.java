package com.sip.sip.controller;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.service.ICargoService;
import com.sip.sip.service.IExplorarProjetosService;
import com.sip.sip.service.ITecnologiaService;
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
    @Autowired
    private ITecnologiaService tecnologiaService;

    @Autowired
    private ICargoService cargoService;

    @GetMapping("")
    public String showProjetos(Model model) {
        List<Projeto> projetos = explorarProjetosService.listarProjetos();
        model.addAttribute("projetos", projetos);
        model.addAttribute("filtroDTO",new FiltroDTO());
        List<Tecnologia> tecnologias = tecnologiaService.listarTecnologias();
        model.addAttribute("tecnologias", tecnologias);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        return "explorar-projetos";
    }

    @PostMapping("")
    public String filtrarProjetos(Model model,@ModelAttribute FiltroDTO filtroDTO) {

        List<Projeto> projetos = explorarProjetosService.filtrarProjetos(filtroDTO);
        List<Tecnologia> tecnologias = tecnologiaService.listarTecnologias();
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("tecnologias", tecnologias);
        model.addAttribute("cargos", cargos);
        model.addAttribute("projetos", projetos);
        return "explorar-projetos";
    }
}
