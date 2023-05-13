package com.sip.sip.service;

import com.sip.sip.dao.MensagemDAO;
import com.sip.sip.dto.MensagemDTO;
import com.sip.sip.dto.MensagemEnviadaDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

	@Override
	public List<Mensagem> listarMensagensPorDestinatario(Usuario dest) {
		return mensagemDAO.listarMensagemPorDestinatario(dest);
	}

	public MensagemDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException {
		Mensagem mensagemExistente = mensagemDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemToMensagemDTO(mensagemExistente);
	}


	public MensagemDTO mensagemToMensagemDTO(Mensagem m) {
		MensagemDTO dto = new MensagemDTO();
		dto.setId(m.getId());

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
			dto.setUsuarioDestinatario(usuarioDestinatario);
		}

		if (m.getTimestamp() == null) {
			dto.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		} else {
			String timestampString = m.getTimestamp();

			// timestamp -> LocalDateTime
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			LocalDateTime dateTime = LocalDateTime.parse(timestampString, inputFormatter);

			// Formata LocalDateTime
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a | dd 'de' MMMM", Locale.forLanguageTag("pt-BR"));
			String formattedDateTime = dateTime.format(outputFormatter);

			dto.setTimestamp(formattedDateTime);
		}
		return dto;
	}
	public Mensagem mensagemDTOToMensagem(MensagemDTO dto) {
		Mensagem mensagem = new Mensagem();

		if(dto.getConteudo() != null) mensagem.setConteudo(dto.getConteudo());

		//todo obter remetente e setar

		if(dto.getUsuarioRemetente() != null) {
			Long remetenteId = dto.getUsuarioRemetente().getSecond();
			Usuario remetente = usuarioService.buscarUsuarioPorId(remetenteId);
			mensagem.setUsuarioRemetente(remetente);
		}

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

	public Mensagem mensagemEnviadaDTOToMensagem(MensagemEnviadaDTO dto) {
		Mensagem mensagem = new Mensagem();

		if(dto.getConteudo() != null) mensagem.setConteudo(dto.getConteudo());

		//todo obter remetente e setar


		Usuario remetente = usuarioService.buscarUsuarioPorId(dto.getUsuarioRemetente());
		mensagem.setUsuarioRemetente(remetente);


		if(dto.getUsuarioDestinatario() != null) {
			Long destinatarioId = dto.getUsuarioDestinatario();
			Usuario destinatario = usuarioService.buscarUsuarioPorId(destinatarioId);
			mensagem.setUsuarioDestinatario(destinatario);
		}

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String timestamp = currentDateTime.format(formatter);

		mensagem.setTimestamp(timestamp);

		return mensagem;
	}

	public Map<Long, List<MensagemDTO>> listarConversas(Usuario usuario) {
		Map<Long, List<Mensagem>> conversas = mensagemDAO.listarConversas(usuario);

		Map<Long, List<MensagemDTO>> conversasDTO = new HashMap<>();

		conversas.forEach((id, mensagens) -> {
			List<MensagemDTO> mensagensDTO = new ArrayList<>();
			mensagens.forEach(mensagem -> mensagensDTO.add(mensagemToMensagemDTO(mensagem)));
			conversasDTO.put(id, mensagensDTO);
		});
		return conversasDTO;
	}


	public MensagemDTO criarMensagem(MensagemEnviadaDTO dto) {
		Mensagem mensagem = mensagemEnviadaDTOToMensagem(dto);

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
