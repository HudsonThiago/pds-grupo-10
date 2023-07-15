package com.sip.sip.framework.controller;

import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.model.Cargo;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Habilidade;
import com.sip.sip.framework.model.Usuario;
import com.sip.sip.framework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private IExplorarProjetosService explorarProjetosService;
    @Autowired
    private IHabilidadeService habilidadeService;
    @Autowired
    private ICargoService cargoService;
    @Autowired
    private ManterUsuarioService usuarioService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String dashboard(Model model) {
        List<Projeto> projetos = explorarProjetosService.listarProjetos();
        model.addAttribute("projetos", projetos);
        model.addAttribute("filtroDTO",new FiltroDTO());
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);

        Usuario usuarioLogado = usuarioService.buscarUsuarioPorId(2l);
        model.addAttribute("usuarioLogado", usuarioLogado);

        List<Projeto> projetosRec = explorarProjetosService.listarProjetosRecomendados(usuarioLogado);
        model.addAttribute("projetosRec", projetosRec);

        List<Projeto> projetosD = explorarProjetosService.listarProjetosDestacados();
        model.addAttribute("projetosD", projetosD);
        return "dashboard/dashboard";
    }

    @PostMapping("")
    public String filtrarProjetos(Model model,@ModelAttribute FiltroDTO filtroDTO) {
        List<Projeto> projetos = explorarProjetosService.filtrarProjetos(filtroDTO);
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("habilidades", habilidades);
        model.addAttribute("cargos", cargos);
        model.addAttribute("projetos", projetos);
        Usuario usuarioLogado = usuarioService.buscarUsuarioPorId(2l);      // todo auth
        model.addAttribute("usuarioLogado", usuarioLogado);
        List<Projeto> projetosRec = explorarProjetosService.listarProjetosRecomendados(usuarioLogado);
        model.addAttribute("projetosRec", projetosRec);
        List<Projeto> projetosD = explorarProjetosService.listarProjetosDestacados();
        model.addAttribute("projetosD", projetosD);
        return "dashboard/dashboard";
    }
}