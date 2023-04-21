package com.sip.sip.controller;

import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.service.IProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private IProjetoService projetoService;



    @GetMapping("")
    public List<Projeto> listarProjetos() {
        return projetoService.listarProjetos();
    }

    @GetMapping("/{id}")
    public Projeto buscarProjetoPorId(@PathVariable Long id) throws ProjetoNotFoundException {
        return projetoService.buscarProjetoPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto criarProjeto(Model model, @ModelAttribute ProjetoDTO projetoDTO) {
        return projetoService.criarProjeto(projetoDTO);
    }

    @PutMapping("/{id}")
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projeto) throws ProjetoNotFoundException {
        return projetoService.atualizarProjeto(id, projeto);
    }

    @DeleteMapping("/{id}")
    public void excluirProjeto(@PathVariable Long id) throws ProjetoNotFoundException {
        projetoService.excluirProjeto(id);
    }
}