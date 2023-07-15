package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.MensagemPrivada;
import com.sip.sip.framework.model.Usuario;

import java.util.List;
import java.util.Map;

public interface MensagemPDAO {

	List<MensagemPrivada> listarMensagens();

	MensagemPrivada buscarMensagemPorId(Long id);

	List<MensagemPrivada> listarMensagemPorDestinatario(Usuario destinatario);

	MensagemPrivada criarMensagem(MensagemPrivada mensagem);

	MensagemPrivada atualizarMensagem(MensagemPrivada mensagem);

	void excluirMensagem(Long id);

	Map<Long, List<MensagemPrivada>> listarConversas(Usuario usuario);
}
