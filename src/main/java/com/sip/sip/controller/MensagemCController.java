package com.sip.sip.controller;

import com.sip.sip.dto.MensagemCDTO;
import com.sip.sip.dto.MensagemCEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.MensagemChat;
import com.sip.sip.service.IMensagemCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mensagensChat")
public class MensagemCController {

    @Autowired
    private IMensagemCService mensagemService;



    @GetMapping("")
    public List<MensagemChat> listarMensagens() {
        return mensagemService.listarMensagens();
    }

    @GetMapping("/{id}")
    public MensagemCDTO buscarMensagemPorId(@PathVariable Long id) throws MensagemNotFoundException {
        return mensagemService.buscarMensagemPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemCDTO criarMensagem(Model model, @ModelAttribute MensagemCEnviadaDTO mensagemCEnviadaDTO) throws IOException, ProjetoNotFoundException {
        return mensagemService.criarMensagem(mensagemCEnviadaDTO);
    }


    @DeleteMapping("/{id}")
    public void excluirMensagem(@PathVariable Long id) throws MensagemNotFoundException {
        mensagemService.excluirMensagem(id);
    }
}