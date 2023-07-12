package com.sip.sip.controller;

import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Habilidade;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ICargoService;
import com.sip.sip.service.IProjetoService;
import com.sip.sip.service.IHabilidadeService;
import com.sip.sip.service.ManterUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ManterProjetoController {
    @Autowired
    private IHabilidadeService habilidadeService;
    @Autowired
    private ICargoService cargoService;
    @Autowired
    private ManterUsuarioService usuarioService;
    @Autowired
    private IProjetoService projetoService;

    @GetMapping("/criar-projeto")
    public String criarProjeto(Model model) {
        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        model.addAttribute("projetoCadastroDTO", new ProjetoCadastroDTO());
        return "criar-projeto";
    }

    @GetMapping("/listar-projetos")
    public String listarProjetos(Model model) {
        // Temporario. Mudar para obter o usuario logado no momento
        Usuario u = new Usuario();
        u.setProjetosParticipados(projetoService.listarProjetos());
        List<Projeto> projetos =  u.getProjetosParticipados();
        model.addAttribute("projetos", projetos);
        return "listar-projetos";
    }

    @GetMapping("/ver-projeto/{id}")
    public String verProjeto(Model model, @PathVariable Long id) throws ProjetoNotFoundException {
        // todo auth
        Usuario principal =  usuarioService.buscarUsuarioPorId(2l);

        ProjetoDTO projeto = projetoService.buscarProjetoPorId(id);
        Boolean ehMembro = projetoService.ehMembro(principal.getId(), projeto.getId());
        ehMembro = true; // test
        model.addAttribute("ehMembro", ehMembro);
        model.addAttribute("projeto", projeto);
        return "ver-projeto";
    }

    @GetMapping("/editar-projeto/{id}")
    public String editarProjeto(Model model, @PathVariable Long id) throws ProjetoNotFoundException {
        // todo auth
        Usuario principal =  usuarioService.buscarUsuarioPorId(2l);

        List<Habilidade> habilidades = habilidadeService.listarHabilidades();
        model.addAttribute("habilidades", habilidades);
        List<Cargo> cargos = cargoService.listarCargos();
        model.addAttribute("cargos", cargos);
        ProjetoCadastroDTO projeto = projetoService.buscarProjetoCadastradoPorId(id);
        model.addAttribute("projetoCadastroDTO", projeto);
        model.addAttribute("id", id);

        return "editar-projeto";
    }


}
