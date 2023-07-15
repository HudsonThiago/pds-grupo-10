package com.sip.sip.framework.service;

import com.sip.sip.framework.dao.MensagemPDAO;
import com.sip.sip.framework.dto.MensagemPDTO;
import com.sip.sip.framework.dto.MensagemPEnviadaDTO;
import com.sip.sip.framework.exception.MensagemNotFoundException;
import com.sip.sip.framework.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class MensagemPService implements IMensagemPService {

	@Autowired
	@Qualifier("MensagemPDAOJPA")
	private MensagemPDAO mensagemPDAO;

	@Autowired
	private ManterUsuarioService usuarioService;



	public List<MensagemPrivada> listarMensagens() {
		return mensagemPDAO.listarMensagens();
	}

	@Override
	public List<MensagemPrivada> listarMensagensPorDestinatario(Usuario dest) {
		return mensagemPDAO.listarMensagemPorDestinatario(dest);
	}

	public MensagemPDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException {
		MensagemPrivada mensagemExistente = mensagemPDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemPToMensagemDTO(mensagemExistente);
	}


	public MensagemPDTO mensagemPToMensagemDTO(MensagemPrivada m) {
		MensagemPDTO dto = new MensagemPDTO();
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
	public MensagemPrivada mensagemPDTOToMensagemP(MensagemPDTO dto) {
		MensagemPrivada mensagem = new MensagemPrivada();

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

	public MensagemPrivada mensagemPEnviadaDTOToMensagemP(MensagemPEnviadaDTO dto) {
		MensagemPrivada mensagem = new MensagemPrivada();

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

	public Map<Long, List<MensagemPDTO>> listarConversas(Usuario usuario) {
		Map<Long, List<MensagemPrivada>> conversas = mensagemPDAO.listarConversas(usuario);

		Map<Long, List<MensagemPDTO>> conversasDTO = new HashMap<>();

		conversas.forEach((id, mensagens) -> {
			List<MensagemPDTO> mensagensDTO = new ArrayList<>();
			mensagens.forEach(mensagem -> mensagensDTO.add(mensagemPToMensagemDTO(mensagem)));
			conversasDTO.put(id, mensagensDTO);
		});
		return conversasDTO;
	}


	public MensagemPDTO criarMensagem(MensagemPEnviadaDTO dto) {
		MensagemPrivada mensagem = mensagemPEnviadaDTOToMensagemP(dto);

		// salvar mensagem
		MensagemPrivada novaMensagem = mensagemPDAO.criarMensagem(mensagem);
		return mensagemPToMensagemDTO(novaMensagem);
	}

	public MensagemPrivada atualizarMensagem(Long id, MensagemPrivada mensagem) throws MensagemNotFoundException {
		MensagemPrivada mensagemExistente = mensagemPDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemPDAO.atualizarMensagem(mensagem);
	}

	public void excluirMensagem(Long id) throws MensagemNotFoundException {
		MensagemPrivada mensagemExistente = mensagemPDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        mensagemPDAO.excluirMensagem(id);

	}

}
