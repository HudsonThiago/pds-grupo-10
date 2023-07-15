package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.MensagemChat;
import com.sip.sip.framework.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("MensagemCDAOJPA")
public class MensagemCDAOJPA implements MensagemCDAO {

	@Autowired
	private MensagemCRepository mensagemCRepository;

	@Override
	public List<MensagemChat> listarMensagens() {
		List<MensagemChat> mensagem = new ArrayList<>();
		mensagemCRepository.findAll()
				.forEach(mensagem::add);
		return mensagem;
	}

	@Override
	public MensagemChat buscarMensagemPorId(Long id) {
		Optional<MensagemChat> mensagemOptional = mensagemCRepository.findById(id);
		return mensagemOptional.orElse(null);
	}
	@Override
	public List<MensagemChat> listarMensagemPorProjetoDestinatario(Projeto projeto) {
		return mensagemCRepository.findByProjetoDestinatario(projeto);

	}
	@Override
	public MensagemChat criarMensagem(MensagemChat mensagem) {
		return mensagemCRepository.save(mensagem);
	}

	@Override
	public MensagemChat atualizarMensagem(MensagemChat mensagem) {
		return mensagemCRepository.save(mensagem);
	}

	@Override
	public void excluirMensagem(Long id) {
		mensagemCRepository.deleteById(id);
	}




}
