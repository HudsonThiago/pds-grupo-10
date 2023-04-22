package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Disponibilidade;
import com.sip.sip.model.Projeto;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.model.Tecnologia;
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
import java.util.List;
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

	public Projeto buscarProjetoPorId(Long id) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
        if (projetoExistente == null) {
            throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
        }
        return projetoExistente;
	}

	private Projeto cadastroDTOToProjeto(ProjetoCadastroDTO dto) {
		Projeto projeto = new Projeto();
		projeto.setNome(dto.getNome());
		projeto.setDescricao(dto.getDescricao());
		projeto.setDisponibilidade(new Disponibilidade(dto.getHorasPorSemana(),
				dto.getDiasPorSemana()));
		projeto.setNumDeVagas(dto.getNumDeVagas());
		List<Long> tecnologiasEscolhidasId = dto.getTecnologiasEscolhidasId();
		List<Tecnologia> tecnologiasEscolhidas =
				tecnologiasEscolhidasId.stream().map((id) -> {
					return tecnologiaDAO.buscarTecnologia(id);
				}).collect(Collectors.toList());
		projeto.setTecnologias((ArrayList<Tecnologia>) tecnologiasEscolhidas);
		return projeto;
	}

	private ProjetoDTO projetoToProjetoDTO(Projeto p) {
		ProjetoDTO dto = new ProjetoDTO();
		dto.setId(p.getId());
		dto.setNome(p.getNome());
		dto.setDescricao(p.getDescricao());
		if (p.getCriador() != null) {
			// set usuario criador
//			dto.setUsuarioCriadorNome(p.getCriador().getNome());
		}
		dto.setDiasPorSemana(p.getDisponibilidade().getDiasPorSemana());
		dto.setHorasPorSemana(p.getDisponibilidade().getHorasPorSemana());

		dto.setNumDeVagas(p.getNumDeVagas());
		List<Long> tecnologiasId = p.getTecnologias().stream()
									.map(tecnologia -> tecnologia.getId()).collect(Collectors.toList());
		dto.setTecnologiasEscolhidasId(tecnologiasId);
		return dto;
	}


	public ProjetoDTO criarProjeto(ProjetoCadastroDTO dto) throws IOException {

		Projeto projeto = cadastroDTOToProjeto(dto);

		// salvar projeto sem imagem
		Projeto novoProjeto = projetoDAO.criarProjeto(projeto);
		Long id = novoProjeto.getId();

		// obter imagem
		MultipartFile arquivo = dto.getImagem();
		BufferedImage imagemOriginal = ImageIO.read(arquivo.getInputStream());
		int type = imagemOriginal.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : imagemOriginal.getType();

		// redimensionar imagem
		BufferedImage imagemRedimensionada = new BufferedImage(200, 100, type);
		Graphics2D g = imagemRedimensionada.createGraphics();
		g.drawImage(imagemOriginal, 0, 0, 200, 100, null);
		g.dispose();


		// renomear arquivo
		String fileName = arquivo.getOriginalFilename();
		String extensao = StringUtils.getFilenameExtension(fileName);
		String novoNome =  "imagemProjeto" + id + "." + extensao;


		// copiar para pasta
		Path path = Paths.get("imagens/", novoNome);
		File arquivoDestino = path.toFile();
		ImageIO.write(imagemRedimensionada, extensao, arquivoDestino);
		novoProjeto.setImagemUrl("/imagens/" + novoNome);

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

}