package com.sip.sip.dao;

import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.model.Usuario;

import java.util.List;
import java.util.Map;

public interface MensagemDAO {

	List<MensagemPrivada> listarMensagens();

	MensagemPrivada buscarMensagemPorId(Long id);

	List<MensagemPrivada> listarMensagemPorDestinatario(Usuario destinatario);

	MensagemPrivada criarMensagem(MensagemPrivada mensagem);

	MensagemPrivada atualizarMensagem(MensagemPrivada mensagem);

	void excluirMensagem(Long id);

	Map<Long, List<MensagemPrivada>> listarConversas(Usuario usuario);
}
