package com.sip.sip.framework.service;


import com.sip.sip.framework.dto.MensagemCDTO;
import com.sip.sip.framework.dto.MensagemCEnviadaDTO;
import com.sip.sip.framework.exception.MensagemNotFoundException;
import com.sip.sip.framework.exception.ProjetoNotFoundException;
import com.sip.sip.framework.model.MensagemChat;

import java.io.IOException;
import java.util.List;

public interface IMensagemCService {
    MensagemCDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException;

    List<MensagemChat> listarMensagens();

    List<MensagemCDTO> listarMensagensPorProjetoDestinatario(Long id) throws ProjetoNotFoundException;

    MensagemCDTO criarMensagem(MensagemCEnviadaDTO projeto) throws IOException, ProjetoNotFoundException;

    MensagemChat atualizarMensagem(Long id, MensagemChat projeto) throws MensagemNotFoundException;

    void excluirMensagem(Long id) throws MensagemNotFoundException;

     MensagemCDTO mensagemCToMensagemCDTO(MensagemChat m);
     MensagemChat mensagemCDTOToMensagemC(MensagemCDTO dto) throws ProjetoNotFoundException;
     MensagemChat mensagemCEnviadaDTOToMensagemC(MensagemCEnviadaDTO dto) throws ProjetoNotFoundException;
     Boolean getUploadEnabled();

}