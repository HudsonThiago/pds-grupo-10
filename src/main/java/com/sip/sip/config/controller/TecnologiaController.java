package com.sip.sip.config.controller;

import com.sip.sip.exception.TecnologiaNotFoundException;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnologias")
public class TecnologiaController {

    @Autowired
    private TecnologiaService tecnologiaService;

    @GetMapping("")
    public List<Tecnologia> listarTecnologias() {
        return tecnologiaService.listarTecnologias();
    }
    @GetMapping("/inativas")
    public List<Tecnologia> listarTecnologiasInativas() {
        return tecnologiaService.listarTecnologiasInativas();
    }

    @GetMapping("/{id}")
    public Tecnologia buscarTecnologiaPorId(@PathVariable Long id) throws TecnologiaNotFoundException {
        return tecnologiaService.buscarTecnologiaPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Tecnologia criarTecnologia(@RequestBody Tecnologia tecnologia) {
        return tecnologiaService.criarTecnologia(tecnologia);
    }

    @PostMapping("/solicitar-tecnologia")
    @ResponseStatus(HttpStatus.CREATED)
    public Tecnologia solicitarTecnologia(@RequestBody Tecnologia tecnologia) {
        tecnologia.setAtivo(false);
        return tecnologiaService.criarTecnologia(tecnologia);
    }


    @PutMapping("/{id}")
    public Tecnologia atualizarTecnologia(@PathVariable Long id, @RequestBody Tecnologia tecnologia) throws TecnologiaNotFoundException {
        return tecnologiaService.atualizarTecnologia(id, tecnologia);
    }

    @DeleteMapping("/{id}")
    public void excluirTecnologia(@PathVariable Long id) throws TecnologiaNotFoundException {
        tecnologiaService.excluirTecnologia(id);
    }
}