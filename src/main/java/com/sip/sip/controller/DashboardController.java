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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private IExplorarProjetosService explorarProjetosService;
    @Autowired
    private ITecnologiaService tecnologiaService;
    @Autowired
    private ICargoService cargoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String dashboard(Model model) {
        List<Projeto> projetos = explorarProjetosService.listarProjetos();
        model.addAttribute("projetos", projetos);
        model.addAttribute("filtroDTO",new FiltroDTO());
        List<Tecnologia> tecnologias = tecnologiaService.listarTecnologias();
        model.addAttribute("tecnologias", tecnologias);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        return "dashboard/dashboard";
    }
}
