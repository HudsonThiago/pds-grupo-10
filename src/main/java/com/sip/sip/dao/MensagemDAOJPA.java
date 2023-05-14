package com.sip.sip.dao;

import com.sip.sip.model.MensagemPrivada;
import com.sip.sip.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("MensagemDAOJPA")
public class MensagemDAOJPA implements MensagemDAO {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Override
	public List<MensagemPrivada> listarMensagens() {
		List<MensagemPrivada> mensagem = new ArrayList<>();
		mensagemRepository.findAll()
				.forEach(mensagem::add);
		return mensagem;
	}

	@Override
	public MensagemPrivada buscarMensagemPorId(Long id) {
		Optional<MensagemPrivada> mensagemOptional = mensagemRepository.findById(id);
		return mensagemOptional.orElse(null);
	}
	@Override
	public List<MensagemPrivada> listarMensagemPorDestinatario(Usuario destinatario) {
		return mensagemRepository.findByUsuarioDestinatario(destinatario);

	}
	@Override
	public MensagemPrivada criarMensagem(MensagemPrivada mensagem) {
		return mensagemRepository.save(mensagem);
	}

	@Override
	public MensagemPrivada atualizarMensagem(MensagemPrivada mensagem) {
		return mensagemRepository.save(mensagem);
	}

	@Override
	public void excluirMensagem(Long id) {
		mensagemRepository.deleteById(id);
	}

	@Override
	public Map<Long, List<MensagemPrivada>> listarConversas(Usuario usuario) {
		List<MensagemPrivada> mensagens = mensagemRepository.findByUsuarioDestinatarioIdOrUsuarioRemetenteId(usuario.getId(),usuario.getId());
		Map<Long, List<MensagemPrivada>> conversas = new HashMap<>();

		for (MensagemPrivada mensagem : mensagens) {
			Long outroUsuarioId = mensagem.getUsuarioRemetente().equals(usuario) ?
					mensagem.getUsuarioDestinatario().getId() :
					mensagem.getUsuarioRemetente().getId();

			if (!conversas.containsKey(outroUsuarioId)) {
				conversas.put(outroUsuarioId, new ArrayList<>());
			}
			conversas.get(outroUsuarioId).add(mensagem);
		}
		return conversas;
	}


}
