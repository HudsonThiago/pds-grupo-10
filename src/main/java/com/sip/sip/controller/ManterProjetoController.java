package com.sip.sip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManterProjetoController {
    @GetMapping("/criar-projeto")
    public String criarProjeto() {
        return "criar-projeto";
    }
}
