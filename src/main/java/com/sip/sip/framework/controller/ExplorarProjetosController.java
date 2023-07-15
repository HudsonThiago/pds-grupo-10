package com.sip.sip.framework.controller;

import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.dto.ProjetoDTO;
import com.sip.sip.framework.exception.ProjetoNotFoundException;
import com.sip.sip.framework.model.Cargo;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Habilidade;
import com.sip.sip.framework.service.ICargoService;
import com.sip.sip.framework.service.IExplorarProjetosService;
import com.sip.sip.framework.service.IProjetoService;
import com.sip.sip.framework.service.IHabilidadeService;
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
    private IProjetoService projetoService;
    @Autowired
    private IHabilidadeService habilidadeService;

    @Autowired
    private ICargoService cargoService;

    @GetMapping("")
    public String showProjetos(Model model) {
        List<Projeto> projetos = explorarProjetosService.listarProjetos();
        model.addAttribute("projetos", projetos);
        model.addAttribute("filtroDTO",new FiltroDTO());
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        return "explorar-projetos";
    }

    @PostMapping("")
    public String filtrarProjetos(Model model,@ModelAttribute FiltroDTO filtroDTO) {
        List<Projeto> projetos = explorarProjetosService.filtrarProjetos(filtroDTO);
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("habilidades", habilidades);
        model.addAttribute("cargos", cargos);
        model.addAttribute("projetos", projetos);
        return "explorar-projetos";
    }

    @GetMapping("/{id}")
    public String visitarProjetos(Model model, @PathVariable  Long id) throws ProjetoNotFoundException {
        ProjetoDTO projeto = projetoService.buscarProjetoPorId(id);
        model.addAttribute("projeto", projeto);
        return "visitar-projeto";
    }
}
