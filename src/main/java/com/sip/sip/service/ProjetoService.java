package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.*;
import com.sip.sip.dto.ProjetoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjetoService implements IProjetoService {

	@Autowired
	@Qualifier("ProjetoDAOJPA")
	private ProjetoDAO projetoDAO;
	@Qualifier("TecnologiaDAOJPA")
	@Autowired
	private TecnologiaDAO tecnologiaDAO;

	public List<Projeto> listarProjetos() {
		return projetoDAO.listarProjetos();
	}

	public ProjetoDTO buscarProjetoPorId(Long id) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
        if (projetoExistente == null) {
            throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
        }
        return projetoToProjetoDTO(projetoExistente);
	}

	private Projeto cadastroDTOToProjeto(ProjetoCadastroDTO dto) {
		Projeto projeto = new Projeto();
		projeto.setNome(dto.getNome());
		projeto.setDescricao(dto.getDescricao());
		projeto.setDisponibilidade(new Disponibilidade(dto.getHorasPorSemana(),
				dto.getDiasPorSemana()));
		projeto.setNumDeVagas(dto.getNumDeVagas());
		List<Long> tecnologiasEscolhidasId = dto.getTecnologiasEscolhidasId();
		if (dto.getTecnologiasEscolhidasId() != null) {
			List<Tecnologia> tecnologiasEscolhidas =
					tecnologiasEscolhidasId.stream().map((id) -> {
						return tecnologiaDAO.buscarTecnologia(id);
					}).collect(Collectors.toList());
			projeto.setTecnologias((ArrayList<Tecnologia>) tecnologiasEscolhidas);
		}
		return projeto;
	}

	private ProjetoDTO projetoToProjetoDTO(Projeto p) {
		ProjetoDTO dto = new ProjetoDTO();
		dto.setId(p.getId());
		dto.setNome(p.getNome());
		dto.setDescricao(p.getDescricao());
		if (p.getCriador() != null) {
			dto.setUsuarioCriadorNome(p.getCriador().getNome());
		}
		dto.setDiasPorSemana(p.getDisponibilidade().getDiasPorSemana());
		dto.setHorasPorSemana(p.getDisponibilidade().getHorasPorSemana());

		dto.setNumDeVagas(p.getNumDeVagas());

		if (dto.getTecnologiasEscolhidasId() != null) {
			Map<String, Long> tecnologiasId = p.getTecnologias().stream()
					.collect(Collectors.toMap(
							tecnologia -> tecnologia.getNome(),
							tecnologia -> tecnologia.getId()
							)
					);
			dto.setTecnologiasEscolhidasId(tecnologiasId);
		}

		dto.setImagemUrl(p.getImagemUrl());

		if (p.getUsuariosProjeto() == null) return dto;
		Map<String, List<String>> usuariosMap = p.getUsuariosProjeto().stream()
				.collect(Collectors.toMap(
				uProj -> uProj.getUsuario().getNome(),
				uProj -> uProj.getCargos().stream().map(Cargo::getNome).collect(Collectors.toList())
		));
		dto.setNomeCargoMap(usuariosMap);

		return dto;
	}


	public ProjetoDTO criarProjeto(ProjetoCadastroDTO dto) throws IOException {

		Projeto projeto = cadastroDTOToProjeto(dto);

		// salvar projeto sem imagem
		Projeto novoProjeto = projetoDAO.criarProjeto(projeto);
		Long id = novoProjeto.getId();
		if(novoProjeto.getImagemUrl() == "/imagens/default.jpg") return projetoToProjetoDTO(novoProjeto);

		// obter imagem
		MultipartFile arquivo = dto.getImagem();
		BufferedImage imagemOriginal = ImageIO.read(arquivo.getInputStream());
		if (imagemOriginal == null) return projetoToProjetoDTO(novoProjeto);
		int type = imagemOriginal.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : imagemOriginal.getType();

		// redimensionar imagem 200x100
		BufferedImage imagemSmall = new BufferedImage(200, 100, type);
		Graphics2D g = imagemSmall.createGraphics();
		g.drawImage(imagemOriginal, 0, 0, 200, 100, null);
		g.dispose();

		//redimensionar imagem 600x300
		BufferedImage imagemBanner = new BufferedImage(600, 300, type);
		Graphics2D g2 = imagemBanner.createGraphics();
		g2.drawImage(imagemOriginal, 0, 0, 600, 300, null);
		g2.dispose();

		// renomear arquivo
		String fileName = arquivo.getOriginalFilename();
		String extensao = StringUtils.getFilenameExtension(fileName);
		String novoNome =  "imagemProjeto" + id + "." + extensao;
		String nomeBanner =  "imagemProjeto" + id + "Banner." + extensao;

		// copiar para pasta
		Path path = Paths.get("imagens/", novoNome);
		File arquivoDestino = path.toFile();
		ImageIO.write(imagemSmall, extensao, arquivoDestino);
		novoProjeto.setImagemUrl("/imagens/" + novoNome);

		// copiar banner para pasta
		Path pathBanner = Paths.get("imagens/", nomeBanner);
		File arquivoDestinoBanner = pathBanner.toFile();
		ImageIO.write(imagemBanner, extensao, arquivoDestinoBanner);

		Projeto p = projetoDAO.atualizarProjeto(novoProjeto);
		return projetoToProjetoDTO(p);
	}

	public Projeto atualizarProjeto(Long id, Projeto projeto) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
        if (projetoExistente == null) {
            throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
        }
        return projetoDAO.atualizarProjeto(projeto);
	}

	public void excluirProjeto(Long id) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
        if (projetoExistente == null) {
            throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
        }
        projetoDAO.excluirProjeto(id);

	}

	public Boolean ehMembro(Long idUsuario, Long idProjeto) {
		Boolean encontrado = false;
		List<Usuario> membros = projetoDAO.buscarProjetoPorId(idProjeto).getMembros();
		for (Usuario membro : membros) {
			if (membro.getId() == idUsuario) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	public Projeto retornarProjetoPorId(Long id) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
		if (projetoExistente == null) {
			throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
		}
		return projetoExistente;
	}

}
