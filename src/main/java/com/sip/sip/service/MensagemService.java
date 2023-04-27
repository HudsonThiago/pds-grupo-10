package com.sip.sip.service;

import com.sip.sip.dao.MensagemDAO;
import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MensagemService implements IMensagemService {

	@Autowired
	@Qualifier("MensagemDAOJPA")
	private MensagemDAO mensagemDAO;

	@Autowired
	private ManterUsuarioService usuarioService;



	public List<Mensagem> listarMensagens() {
		return mensagemDAO.listarMensagens();
	}

	public MensagemDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException {
		Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemToMensagemDTO(mensagemExistente);
	}


	private MensagemDTO mensagemToMensagemDTO(Mensagem m) {
		MensagemDTO dto = new MensagemDTO();
		dto.setId(m.getId());

		if(m.getTitulo() != null) dto.setTitulo(m.getTitulo());
		if(m.getConteudo() != null) dto.setConteudo(m.getConteudo());

		if(m.getUsuarioRemetente() != null) {
			Pair<String, Long> usuarioRemetente = Pair.of(
					m.getUsuarioRemetente().getNome(),
					m.getUsuarioRemetente().getId());
			dto.setUsuarioRemetente(usuarioRemetente);
		}
		if(m.getUsuarioDestinatario() != null) {
			Pair<String, Long> usuarioDestinatario = Pair.of(
					m.getUsuarioDestinatario().getNome(),
					m.getUsuarioDestinatario().getId());
			dto.setUsuarioRemetente(usuarioDestinatario);
		}
		dto.setTimestamp(m.getTimestamp());
		return dto;
	}
	private Mensagem mensagemDTOToMensagem(MensagemDTO dto) {
		Mensagem mensagem = new Mensagem();

		if(dto.getTitulo() != null) mensagem.setTitulo(dto.getTitulo());
		if(dto.getConteudo() != null) mensagem.setConteudo(dto.getConteudo());

		//todo obter remetente e setar


		if(dto.getUsuarioDestinatario() != null) {
			Long destinatarioId = dto.getUsuarioDestinatario().getSecond();
			Usuario destinatario = usuarioService.buscarUsuarioPorId(destinatarioId);
			mensagem.setUsuarioDestinatario(destinatario);
		}

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String timestamp = currentDateTime.format(formatter);

		mensagem.setTimestamp(timestamp);

		return mensagem;
	}




	public MensagemDTO criarMensagem(MensagemDTO dto) {
		Mensagem mensagem = mensagemDTOToMensagem(dto);

		// salvar mensagem
		Mensagem novaMensagem = mensagemDAO.criarMensagem(mensagem);
		return mensagemToMensagemDTO(novaMensagem);
	}

	public Mensagem atualizarMensagem(Long id, Mensagem mensagem) throws MensagemNotFoundException {
		Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemDAO.atualizarMensagem(mensagem);
	}

	public void excluirMensagem(Long id) throws MensagemNotFoundException {
		Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        mensagemDAO.excluirMensagem(id);

	}

}
