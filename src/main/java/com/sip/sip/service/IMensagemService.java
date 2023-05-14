package com.sip.sip.service;


import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.dto.MensagemEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.model.Usuario;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IMensagemService {
    MensagemDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException;

    List<MensagemPrivada> listarMensagens();

    public List<MensagemPrivada> listarMensagensPorDestinatario(Usuario dest);

    MensagemDTO criarMensagem(MensagemEnviadaDTO projeto) throws IOException;

    MensagemPrivada atualizarMensagem(Long id, MensagemPrivada projeto) throws MensagemNotFoundException;

    void excluirMensagem(Long id) throws MensagemNotFoundException;

     MensagemDTO mensagemToMensagemDTO(MensagemPrivada m);
     MensagemPrivada mensagemDTOToMensagem(MensagemDTO dto);
     MensagemPrivada mensagemEnviadaDTOToMensagem(MensagemEnviadaDTO dto);
     Map<Long, List<MensagemDTO>> listarConversas(Usuario usuario);
}