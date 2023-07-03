package com.sip.sip.service;

import com.sip.sip.dao.MensagemCDAO;
import com.sip.sip.dto.*;
import com.sip.sip.dto.MensagemCDTO;
import com.sip.sip.exception.MensagemNotFoundException;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.MensagemChat;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MensagemCService implements IMensagemCService {

	@Autowired
	@Qualifier("MensagemCDAOJPA")
	private MensagemCDAO mensagemCDAO;

	@Autowired
	private ManterUsuarioService usuarioService;
	@Autowired
	private ProjetoService projetoService;


	public List<MensagemChat> listarMensagens() {
		return mensagemCDAO.listarMensagens();
	}

	@Override
	public List<MensagemCDTO> listarMensagensPorProjetoDestinatario(Long id) throws ProjetoNotFoundException {
		Projeto projeto = projetoService.retornarProjetoPorId(id);
		List<MensagemChat> mensagens = mensagemCDAO.listarMensagemPorProjetoDestinatario(projeto);
		return mensagens.stream().map(mensagem -> mensagemCToMensagemCDTO(mensagem)).collect(Collectors.toList());
	}

	public MensagemCDTO buscarMensagemPorId(Long id) throws MensagemNotFoundException {
		MensagemChat mensagemExistente = mensagemCDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemCToMensagemCDTO(mensagemExistente);
	}


	public MensagemCDTO mensagemCToMensagemCDTO(MensagemChat m) {
		MensagemCDTO dto = new MensagemCDTO();
		dto.setId(m.getId());

		if(m.getConteudo() != null) dto.setConteudo(m.getConteudo());

		if(m.getUsuarioRemetente() != null) {
			Pair<String, Long> usuarioRemetente = Pair.of(
					m.getUsuarioRemetente().getNome(),
					m.getUsuarioRemetente().getId());
			dto.setUsuarioRemetente(usuarioRemetente);
		}
		if(m.getProjetoDestinatario() != null) {
			Long projetoDestinatario = m.getProjetoDestinatario().getId();
			dto.setProjetoDestinatario(projetoDestinatario);
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

			if (m.getArquivoNome() != null) dto.setArquivoNome(m.getArquivoNome());
			if (m.getArquivoURI() != null) dto.setArquivoURI(m.getArquivoURI());
		}
		return dto;
	}
	public MensagemChat mensagemCDTOToMensagemC(MensagemCDTO dto) throws ProjetoNotFoundException {
		MensagemChat mensagem = new MensagemChat();

		if(dto.getConteudo() != null) mensagem.setConteudo(dto.getConteudo());

		//todo obter remetente e setar

		if(dto.getUsuarioRemetente() != null) {
			Long remetenteId = dto.getUsuarioRemetente().getSecond();
			Usuario remetente = usuarioService.buscarUsuarioPorId(remetenteId);
			mensagem.setUsuarioRemetente(remetente);
		}

		if(dto.getProjetoDestinatario() != null) {
			Long destinatarioId = dto.getProjetoDestinatario();
			Projeto destinatario = projetoService.retornarProjetoPorId(destinatarioId);
			mensagem.setProjetoDestinatario(destinatario);
		}

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String timestamp = currentDateTime.format(formatter);

		mensagem.setTimestamp(timestamp);

		return mensagem;
	}

	public MensagemChat mensagemCEnviadaDTOToMensagemC(MensagemCEnviadaDTO dto) throws ProjetoNotFoundException {
		MensagemChat mensagem = new MensagemChat();

		if(dto.getConteudo() != null) mensagem.setConteudo(dto.getConteudo());

		//todo obter remetente e setar


		Usuario remetente = usuarioService.buscarUsuarioPorId(dto.getUsuarioRemetente());
		mensagem.setUsuarioRemetente(remetente);


		if(dto.getProjetoDestinatario() != null) {
			Long destinatarioId = dto.getProjetoDestinatario();
			Projeto destinatario = projetoService.retornarProjetoPorId(destinatarioId);
			mensagem.setProjetoDestinatario(destinatario);
		}

		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		String timestamp = currentDateTime.format(formatter);

		mensagem.setTimestamp(timestamp);

		if (dto.getFile() != null) {
			MultipartFile file = dto.getFile();
			String originalFileName = file.getOriginalFilename();
			String uniqueFileName = System.currentTimeMillis() + "_" + UUID.randomUUID() + "_" + originalFileName;

			Path destinationFolder = Paths.get("upload");
			Path destinationPath = destinationFolder.resolve(uniqueFileName);

			try {
				Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
				mensagem.setArquivoNome(originalFileName);
				mensagem.setArquivoURI("/"+destinationPath);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		return mensagem;
	}


	public MensagemCDTO criarMensagem(MensagemCEnviadaDTO dto) throws ProjetoNotFoundException {
		MensagemChat mensagem = mensagemCEnviadaDTOToMensagemC(dto);

		// salvar mensagem
		MensagemChat novaMensagem = mensagemCDAO.criarMensagem(mensagem);
		return mensagemCToMensagemCDTO(novaMensagem);
	}

	public MensagemChat atualizarMensagem(Long id, MensagemChat mensagem) throws MensagemNotFoundException {
		MensagemChat mensagemExistente = mensagemCDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        return mensagemCDAO.atualizarMensagem(mensagem);
	}

	public void excluirMensagem(Long id) throws MensagemNotFoundException {
		MensagemChat mensagemExistente = mensagemCDAO.buscarMensagemPorId(id);
        if (mensagemExistente == null) {
            throw new MensagemNotFoundException("Mensagem não encontrado com id: " + id);
        }
        mensagemCDAO.excluirMensagem(id);

	}

}
