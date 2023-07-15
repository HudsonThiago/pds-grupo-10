package com.sip.sip.framework.controller;

import com.sip.sip.framework.model.Cidade;
import com.sip.sip.framework.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cidades")
public class CidadeController {
    @Autowired
    CidadeService cidadeService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String listarCidadesParecidas(@RequestParam("term") String term) {
        return cidadeService.listarCidadesParecidas(term);
    }

    @GetMapping("")
    public List<Cidade> listarCidades() {
        return cidadeService.listarCidades();
    }

    @GetMapping("/{id}")
    public Cidade buscarCidadePorId(@PathVariable Long id) {
        return cidadeService.buscarCidadePorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade criarCidade(@RequestBody Cidade cidade) {
        return cidadeService.criarCidade(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizarCidade(@RequestBody Cidade cidade) {
        return cidadeService.atualizarCidade(cidade);
    }

    @DeleteMapping("/{id}")
    public void excluirCidade(@PathVariable Long id)  {
        cidadeService.excluirCidade(id);
    }

}
