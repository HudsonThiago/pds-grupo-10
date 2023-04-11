package com.sip.sip.controller;

import com.sip.sip.model.Tecnologia;
import com.sip.sip.service.ITecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ManterProjetoController {
    @Autowired
    private ITecnologiaService tecnologiaService;
    @GetMapping("/criar-projeto")
    public String criarProjeto(Model model) {
        List<Tecnologia> tecnologias = tecnologiaService.listarTecnologias();
        model.addAttribute("tecnologias", tecnologias);
        return "criar-projeto";
    }
}
