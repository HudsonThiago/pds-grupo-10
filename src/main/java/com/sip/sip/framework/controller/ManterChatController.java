package com.sip.sip.framework.controller;

import com.sip.sip.framework.dto.MensagemCDTO;
import com.sip.sip.framework.dto.ProjetoDTO;
import com.sip.sip.framework.exception.ProjetoNotFoundException;
import com.sip.sip.framework.model.Usuario;
import com.sip.sip.framework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ManterChatController {
    @Autowired
    private IMensagemCService mensagemService;

    @Autowired
    private IProjetoService projetoService;

    @Autowired
    private ManterUsuarioService usuarioService;


    @GetMapping("/{projetoId}")
    public String showChatProjeto(Model model, @PathVariable Long projetoId) throws ProjetoNotFoundException {
        //todo auth
        Usuario principal = usuarioService.buscarUsuarioPorId(2l);
        ProjetoDTO projeto = projetoService.buscarProjetoPorId(projetoId);
        List<MensagemCDTO> mensagens = mensagemService.listarMensagensPorProjetoDestinatario(projetoId);

        model.addAttribute("mensagens", mensagens);
        model.addAttribute("principal", principal);
        model.addAttribute("projeto", projeto);
        model.addAttribute("uploadEnabled", mensagemService.getUploadEnabled());

        return "chat";
    }



}
