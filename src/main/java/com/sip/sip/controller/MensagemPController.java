package com.sip.sip.controller;

import com.sip.sip.dto.MensagemPDTO;
import com.sip.sip.dto.MensagemPEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.service.IMensagemPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mensagens")
public class MensagemPController {

    @Autowired
    private IMensagemPService mensagemService;



    @GetMapping("")
    public List<MensagemPrivada> listarMensagens() {
        return mensagemService.listarMensagens();
    }

    @GetMapping("/{id}")
    public MensagemPDTO buscarMensagemPorId(@PathVariable Long id) throws MensagemNotFoundException {
        return mensagemService.buscarMensagemPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public MensagemPDTO criarMensagem(Model model, @RequestBody MensagemPEnviadaDTO mensagemPEnviadaDTO) throws IOException {
        return mensagemService.criarMensagem(mensagemPEnviadaDTO);
    }
    
    @DeleteMapping("/{id}")
    public void excluirMensagem(@PathVariable Long id) throws MensagemNotFoundException {
        mensagemService.excluirMensagem(id);
    }
}