package com.sip.sip.controller;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.exception.TecnologiaNotFoundException;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.service.TecnologiaService;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ManterTecnologiaController {

    @Autowired
    private TecnologiaService tecnologiaService;


    @GetMapping("solicitar-tecnologia")
    public String solicitarTecnologia() {
        return "solicitar-tecnologia";
    }

    @PostMapping("solicitar-tecnologia")
    public String solicitarTecnologia(Tecnologia tecnologia) {
        tecnologiaService.solicitarTecnologia(tecnologia);
        return "solicitar-tecnologia";
    }
}