package com.sip.sip.framework.service;


import com.sip.sip.framework.dto.MensagemPDTO;
import com.sip.sip.framework.dto.MensagemPEnviadaDTO;
import com.sip.sip.framework.exception.MensagemNotFoundException;
import com.sip.sip.framework.model.MensagemPrivada;
import com.sip.sip.framework.model.Usuario;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IMensagemPService {
    MensagemPDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException;

    List<MensagemPrivada> listarMensagens();

    public List<MensagemPrivada> listarMensagensPorDestinatario(Usuario dest);

    MensagemPDTO criarMensagem(MensagemPEnviadaDTO projeto) throws IOException;

    MensagemPrivada atualizarMensagem(Long id, MensagemPrivada projeto) throws MensagemNotFoundException;

    void excluirMensagem(Long id) throws MensagemNotFoundException;

     MensagemPDTO mensagemPToMensagemDTO(MensagemPrivada m);
     MensagemPrivada mensagemPDTOToMensagemP(MensagemPDTO dto);
     MensagemPrivada mensagemPEnviadaDTOToMensagemP(MensagemPEnviadaDTO dto);
     Map<Long, List<MensagemPDTO>> listarConversas(Usuario usuario);
}