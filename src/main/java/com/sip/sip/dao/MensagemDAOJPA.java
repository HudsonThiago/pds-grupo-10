package com.sip.sip.dao;

import com.sip.sip.model.Mensagem;
import com.sip.sip.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository("MensagemDAOJPA")
public class MensagemDAOJPA implements MensagemDAO {

	@Autowired
	private MensagemRepository mensagemRepository;

	@Override
	public List<Mensagem> listarMensagens() {
		List<Mensagem> mensagem = new ArrayList<>();
		mensagemRepository.findAll()
				.forEach(mensagem::add);
		return mensagem;
	}

	@Override
	public Mensagem buscarMensagemPorId(Long id) {
		Optional<Mensagem> mensagemOptional = mensagemRepository.findById(id);
		return mensagemOptional.orElse(null);
	}
	@Override
	public List<Mensagem> listarMensagemPorDestinatario(Usuario destinatario) {
		return mensagemRepository.findByUsuarioDestinatario(destinatario);

	}
	@Override
	public Mensagem criarMensagem(Mensagem mensagem) {
		return mensagemRepository.save(mensagem);
	}

	@Override
	public Mensagem atualizarMensagem(Mensagem mensagem) {
		return mensagemRepository.save(mensagem);
	}

	@Override
	public void excluirMensagem(Long id) {
		mensagemRepository.deleteById(id);
	}

	@Override
	public Map<Long, List<Mensagem>> listarConversas(Usuario usuario) {
		List<Mensagem> mensagens = mensagemRepository.findByUsuarioDestinatarioIdOrUsuarioRemetenteId(usuario.getId(),usuario.getId());
		Map<Long, List<Mensagem>> conversas = new HashMap<>();

		for (Mensagem mensagem : mensagens) {
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
