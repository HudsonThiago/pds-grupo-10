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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/curtir")
public class CurtirProjetoController {
    @Autowired
    ManterUsuarioService usuarioService;
    @Autowired
    ProjetoService projetoService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Integer>> curtirProjeto(@PathVariable("id") Long projetoId) throws ProjetoNotFoundException {
        Long usuarioId = 2l; // todo auth
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        int numCurtidas = projetoService.curtirProjeto(projetoId, usuario);

            Map<String, Integer> response = new HashMap<>();
        if (numCurtidas == -1) {
            response.put("numCurtidas", null);
            return ResponseEntity.ok(response);
        }
        Projeto projeto = projetoService.retornarProjetoPorId(projetoId);
        usuarioService.curtirProjeto(projeto, usuario);
        response.put("numCurtidas", numCurtidas);
        return ResponseEntity.ok(response);
    }
}
