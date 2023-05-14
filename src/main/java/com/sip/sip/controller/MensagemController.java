package com.sip.sip.controller;

import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.dto.MensagemEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.service.IMensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    @Autowired
    private IMensagemService mensagemService;



    @GetMapping("")
    public List<MensagemPrivada> listarMensagens() {
        return mensagemService.listarMensagens();
    }

    @GetMapping("/{id}")
    public MensagemDTO buscarMensagemPorId(@PathVariable Long id) throws MensagemNotFoundException {
        return mensagemService.buscarMensagemPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemDTO criarMensagem(Model model, @RequestBody MensagemEnviadaDTO mensagemEnviadaDTO) throws IOException {
        return mensagemService.criarMensagem(mensagemEnviadaDTO);
    }
    
    @DeleteMapping("/{id}")
    public void excluirMensagem(@PathVariable Long id) throws MensagemNotFoundException {
        mensagemService.excluirMensagem(id);
    }
}