package com.sip.sip.controller;

import com.sip.sip.dto.MensagemPDTO;
import com.sip.sip.model.*;
import com.sip.sip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mensagens-privadas")
public class ManterMensagensPrivadasController {
    @Autowired
    private IExplorarProjetosService explorarProjetosService;
    @Autowired
    private IHabilidadeService habilidadeService;

    @Autowired
    private IMensagemPService mensagemService;

    @Autowired
    private ManterUsuarioService usuarioService;
    @GetMapping("/")
    public String showMensagens(Model model) {
        Usuario principal = usuarioService.buscarUsuarioPorId(2l);
        Map<Long, List<MensagemPDTO>> conversas = mensagemService.listarConversas(principal);
        model.addAttribute("conversas", conversas);
        model.addAttribute("principal", principal);
        model.addAttribute("outroUsuarioId", conversas.keySet().stream().findFirst().get());
        return "mensagens-privadas";
    }

    @GetMapping("/{outroUsuarioId}")
    public String showMensagensParaUsuario(Model model, @PathVariable Long outroUsuarioId) {
        Usuario principal = usuarioService.buscarUsuarioPorId(2l);
        Map<Long, List<MensagemPDTO>> conversas = mensagemService.listarConversas(principal);
        model.addAttribute("conversas", conversas);
        model.addAttribute("principal", principal);
        model.addAttribute("outroUsuarioId", outroUsuarioId);
        return "mensagens-privadas";
    }



}
