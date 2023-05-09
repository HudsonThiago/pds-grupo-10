package com.sip.sip.service;


import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.dto.MensagemEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.Mensagem;
import com.sip.sip.model.Usuario;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IMensagemService {
    MensagemDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException;

    List<Mensagem> listarMensagens();

    public List<Mensagem> listarMensagensPorDestinatario(Usuario dest);

    MensagemDTO criarMensagem(MensagemEnviadaDTO projeto) throws IOException;

    Mensagem atualizarMensagem(Long id, Mensagem projeto) throws MensagemNotFoundException;

    void excluirMensagem(Long id) throws MensagemNotFoundException;

     MensagemDTO mensagemToMensagemDTO(Mensagem m);
     Mensagem mensagemDTOToMensagem(MensagemDTO dto);
     Mensagem mensagemEnviadaDTOToMensagem(MensagemEnviadaDTO dto);
     Map<Long, List<MensagemDTO>> listarConversas(Usuario usuario);
}