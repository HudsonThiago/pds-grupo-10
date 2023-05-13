package com.sip.sip.controller;

import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ManterChatController {
    @Autowired
    private IExplorarProjetosService explorarProjetosService;
    @Autowired
    private ITecnologiaService tecnologiaService;

    @Autowired
    private IMensagemService mensagemService;

    @Autowired
    private IProjetoService projetoService;

    @Autowired
    private ManterUsuarioService usuarioService;


    @GetMapping("/{projetoId}")
    public String showChatProjeto(Model model, @PathVariable Long projetoId) throws ProjetoNotFoundException {
        //todo auth
        Usuario principal = usuarioService.buscarUsuarioPorId(2l);
        ProjetoDTO projeto = projetoService.buscarProjetoPorId(projetoId);
        Map<Long, List<MensagemDTO>> conversas = mensagemService.listarConversas(principal);

        model.addAttribute("conversas", conversas);
        model.addAttribute("principal", principal);
        model.addAttribute("projeto", projeto);
        // debug
        model.addAttribute("outroUsuarioId", 3l);
        return "chat";
    }



}
