package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.MensagemChat;
import com.sip.sip.framework.model.Projeto;

import java.util.List;

public interface MensagemCDAO {

	List<MensagemChat> listarMensagens();

	MensagemChat buscarMensagemPorId(Long id);

	List<MensagemChat> listarMensagemPorProjetoDestinatario(Projeto projeto);

	MensagemChat criarMensagem(MensagemChat mensagem);

	MensagemChat atualizarMensagem(MensagemChat mensagem);

	void excluirMensagem(Long id);


}
