package com.sip.sip.dao;

import com.sip.sip.model.MensagemChat;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;

import java.util.List;
import java.util.Map;

public interface MensagemCDAO {

	List<MensagemChat> listarMensagens();

	MensagemChat buscarMensagemPorId(Long id);

	List<MensagemChat> listarMensagemPorProjetoDestinatario(Projeto projeto);

	MensagemChat criarMensagem(MensagemChat mensagem);

	MensagemChat atualizarMensagem(MensagemChat mensagem);

	void excluirMensagem(Long id);


}
