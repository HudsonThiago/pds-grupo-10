package com.sip.sip.controller;

import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ManterUsuarioService;
import com.sip.sip.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/favoritar")
public class FavoritarProjetoController {
    @Autowired
    ManterUsuarioService usuarioService;
    @Autowired
    ProjetoService projetoService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Integer>> favoritarProjeto(@PathVariable("id") Long projetoId) throws ProjetoNotFoundException {
        Long usuarioId = 2l; // todo auth
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        int numFavoritos = projetoService.favoritarProjeto(projetoId, usuario);

        Map<String, Integer> response = new HashMap<>();
        Projeto projeto = projetoService.retornarProjetoPorId(projetoId);
        usuarioService.favoritarProjeto(projeto, usuario);
        response.put("numFavoritos", numFavoritos);
        return ResponseEntity.ok(response);
    }
}
