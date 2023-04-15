package com.sip.sip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

        @GetMapping("home/cadastro")
        public String cadastroUsuario() {
            return "cadastro";
        }
    }
