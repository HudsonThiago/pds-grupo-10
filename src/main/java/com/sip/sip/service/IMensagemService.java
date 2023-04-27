package com.sip.sip.service;


import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.Mensagem;


import java.io.IOException;
import java.util.List;

public interface IMensagemService {
    MensagemDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException;

    List<Mensagem> listarMensagens();

    MensagemDTO criarMensagem(MensagemDTO projeto) throws IOException;

    Mensagem atualizarMensagem(Long id, Mensagem projeto) throws MensagemNotFoundException;

    void excluirMensagem(Long id) throws MensagemNotFoundException;
}