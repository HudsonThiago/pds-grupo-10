package com.sip.sip.controller;

import com.sip.sip.exception.HabilidadeNotFoundException;
import com.sip.sip.model.Habilidade;
import com.sip.sip.service.HabilidadeService;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    @GetMapping("")
    public List<Habilidade> listarHabilidades() {
        return habilidadeService.listarHabilidades();
    }
    @GetMapping("/inativas")
    public List<Habilidade> listarHabilidadesInativas() {
        return habilidadeService.listarHabilidadesInativas();
    }

    @GetMapping("/{id}")
    public Habilidade buscarHabilidadePorId(@PathVariable Long id) throws HabilidadeNotFoundException {
        return habilidadeService.buscarHabilidadePorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Habilidade criarHabilidade(@RequestBody Habilidade habilidade) {
        return habilidadeService.criarHabilidade(habilidade);
    }

    @PostMapping("/solicitar-habilidade")
    @ResponseStatus(HttpStatus.CREATED)
    public Habilidade solicitarHabilidade(@RequestBody Habilidade habilidade) {
        UsuarioLogado usuarioLogado = new UsuarioLogado();
        habilidade.setAtivo(false);
        habilidade.setIdUsuarioQueSolicitou(usuarioLogado.id);
        return habilidadeService.criarHabilidade(habilidade);
    }


    @PutMapping("/{id}")
    public Habilidade atualizarHabilidade(@PathVariable Long id, @RequestBody Habilidade habilidade) throws HabilidadeNotFoundException {
        return habilidadeService.atualizarHabilidade(id, habilidade);
    }

    @DeleteMapping("/{id}")
    public void excluirHabilidade(@PathVariable Long id) throws HabilidadeNotFoundException {
        habilidadeService.excluirHabilidade(id);
    }
}