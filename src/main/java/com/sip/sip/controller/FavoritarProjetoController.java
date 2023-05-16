package com.sip.sip.controller;

import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ManterUsuarioService;
import com.sip.sip.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
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
    public ResponseEntity<Map<String, Object>> favoritarProjeto(@PathVariable("id") Long id) throws ProjetoNotFoundException {
        Usuario principal = usuarioService.buscarUsuarioPorId(2l); //todo auth
        Projeto projeto = projetoService.retornarProjetoPorId(id);
        usuarioService.favoritarProjeto(projeto, principal.getId());

        Map<String, Object> response = Collections.emptyMap();
        return ResponseEntity.ok(response);
    }
}
