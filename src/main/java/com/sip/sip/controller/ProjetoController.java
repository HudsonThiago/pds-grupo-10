package com.sip.sip.controller;

import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Disponibilidade;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.ProjetoDTO;
import com.sip.sip.service.IManterProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private IManterProjetoService manterProjetoService;



    @GetMapping("")
    public List<Projeto> listarProjetos() {
        return manterProjetoService.listarProjetos();
    }

    @GetMapping("/{id}")
    public Projeto buscarProjetoPorId(@PathVariable Long id) throws ProjetoNotFoundException {
        return manterProjetoService.buscarProjetoPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto criarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        Disponibilidade d = new Disponibilidade();
        d.setDiasPorSemana(projetoDTO.getDisponibilidadeDiasPorSemana());
        d.setHorasPorSemana(projetoDTO.getDisponibilidadeHorasPorSemana());
        projeto.setDisponibilidade(d);
        return manterProjetoService.criarProjeto(projeto);
    }

    @PutMapping("/{id}")
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) throws ProjetoNotFoundException {
        return manterProjetoService.atualizarProjeto(id, projeto);
    }

    @DeleteMapping("/{id}")
    public void excluirProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        manterProjetoService.excluirProjeto(id);
    }
}