package com.sip.sip.framework.controller;

import com.sip.sip.framework.dto.MensagemCDTO;
import com.sip.sip.framework.dto.MensagemCEnviadaDTO;
import com.sip.sip.framework.exception.MensagemNotFoundException;
import com.sip.sip.framework.model.MensagemChat;
import com.sip.sip.framework.service.IMensagemCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> criarMensagem(@ModelAttribute MensagemCEnviadaDTO mensagemCEnviadaDTO) {
        try {
            mensagemService.criarMensagem(mensagemCEnviadaDTO);
            return ResponseEntity.ok("");
        } catch (UnsupportedOperationException e) {
            String mensagemErro = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }



    @DeleteMapping("/{id}")
    public void excluirMensagem(@PathVariable Long id) throws MensagemNotFoundException {
        mensagemService.excluirMensagem(id);
    }
}