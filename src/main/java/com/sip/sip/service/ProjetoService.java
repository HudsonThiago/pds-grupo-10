package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.HabilidadeDAO;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjetoService implements IProjetoService {

	@Autowired
	@Qualifier("ProjetoDAOJPA")
	private ProjetoDAO projetoDAO;
	@Qualifier("HabilidadeDAOJPA")
	@Autowired
	private HabilidadeDAO habilidadeDAO;
	@Autowired
	private ICargoService cargoService;

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

	public ProjetoCadastroDTO buscarProjetoCadastradoPorId(Long id) throws ProjetoNotFoundException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
		if (projetoExistente == null) {
			throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
		}
		return projetoToCadastroDTO(projetoExistente);
	}

	private ProjetoCadastroDTO projetoToCadastroDTO(Projeto projeto) {
		ProjetoCadastroDTO dto = new ProjetoCadastroDTO();
		dto.setNome(projeto.getNome());
		dto.setDescricao(projeto.getDescricao());
		dto.setHorasPorSemana(projeto.getDisponibilidade().getHorasPorSemana());
		dto.setDiasPorSemana(projeto.getDisponibilidade().getDiasPorSemana());
		dto.setNumDeVagas(projeto.getNumDeVagas());

		List<Long> habilidadesEscolhidasId = projeto.getHabilidades().stream().map(habilidade -> habilidade.getId()).collect(Collectors.toList());
		dto.setHabilidadesEscolhidasId(habilidadesEscolhidasId);
		return dto;
	}

	private Projeto cadastroDTOToProjeto(ProjetoCadastroDTO dto) {
		Projeto projeto = new Projeto();
		projeto.setNome(dto.getNome());
		projeto.setDescricao(dto.getDescricao());
		Disponibilidade d = new Disponibilidade(dto.getHorasPorSemana(),
				dto.getDiasPorSemana());
		projeto.setDisponibilidade(d);
		projeto.setNumDeVagas(dto.getNumDeVagas());
		projeto.setNumCurtidas(dto.getNumCurtidas());
		List<Long> habilidadesEscolhidasId = dto.getHabilidadesEscolhidasId();
		if (dto.getHabilidadesEscolhidasId() != null) {
			List<Habilidade> habilidadesEscolhidas =
					habilidadesEscolhidasId.stream().map((id) -> {
						return habilidadeDAO.buscarHabilidade(id);
					}).collect(Collectors.toList());
			projeto.setHabilidades((ArrayList<Habilidade>) habilidadesEscolhidas);
		}
		if (dto.getImagem() != null) {
			if (!dto.getImagem().isEmpty()) {
				projeto.setImagemUrl(null);
			}
		}

		if (dto.getCargosEscolhidosId() != null) {
			List<Long> cargoIds = dto.getCargosEscolhidosId();

			List<Cargo> cargos = new ArrayList<>();

			for (Long cargoId : cargoIds) {
				Cargo cargo = cargoService.buscarCargoPorId(cargoId);

				// Add the fetched Cargo object to the list
				cargos.add(cargo);
			}
			projeto.setCargosDesejados(cargos);
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

		if (p.getHabilidades() != null) {
			Map<String, Long> habilidadesId = p.getHabilidades().stream()
					.collect(Collectors.toMap(
							habilidade -> habilidade.getNome(),
							habilidade -> habilidade.getId()
							)
					);
			dto.setHabilidadesEscolhidasId(habilidadesId);
		}

		dto.setImagemUrl(p.getImagemUrl());

		if (p.getUsuariosProjeto() == null) return dto;
		Map<String, List<String>> usuariosMap = p.getUsuariosProjeto().stream()
				.collect(Collectors.toMap(
				uProj -> uProj.getUsuario().getNome(),
				uProj -> uProj.getCargos().stream().map(Cargo::getNome).collect(Collectors.toList())
		));
		dto.setNomeCargoMap(usuariosMap);
		dto.setEmDestaque(p.getEmDestaque());
		return dto;
	}


	public ProjetoDTO criarProjeto(ProjetoCadastroDTO dto) throws IOException {

		Projeto projeto = cadastroDTOToProjeto(dto);

		// salvar projeto sem imagem
		Projeto novoProjeto = projetoDAO.criarProjeto(projeto);
		Long id = novoProjeto.getId();
		if (novoProjeto.getImagemUrl() != null) return projetoToProjetoDTO(novoProjeto);

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

	public ProjetoCadastroDTO atualizarProjeto(Long id, ProjetoCadastroDTO dto) throws ProjetoNotFoundException, IOException {
		Projeto projetoExistente = projetoDAO.buscarProjetoPorId(id);
        if (projetoExistente == null) {
            throw new ProjetoNotFoundException("Projeto não encontrado com id: " + id);
        }

		Projeto projeto = cadastroDTOToProjeto(dto);
		projeto.setId(id);
		Long dispId = projetoExistente.getDisponibilidade().getId();
		Disponibilidade disp = projeto.getDisponibilidade();
		disp.setId(dispId);
		projeto.setDisponibilidade(disp);

		// salvar projeto sem imagem
		projetoDAO.atualizarProjeto(projeto);

		if(projeto.getImagemUrl() != null) return projetoToCadastroDTO(projeto);

		// obter imagem
		MultipartFile arquivo = dto.getImagem();
		BufferedImage imagemOriginal = ImageIO.read(arquivo.getInputStream());
		if (imagemOriginal == null) return projetoToCadastroDTO(projeto);
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
		projeto.setImagemUrl("/imagens/" + novoNome);

		// copiar banner para pasta
		Path pathBanner = Paths.get("imagens/", nomeBanner);
		File arquivoDestinoBanner = pathBanner.toFile();
		ImageIO.write(imagemBanner, extensao, arquivoDestinoBanner);

		Projeto p = projetoDAO.atualizarProjeto(projeto);


		return projetoToCadastroDTO(p);
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

	@Override
	public void salvarProjeto(Projeto projeto) {
		projetoDAO.atualizarProjeto(projeto);
	}

	public int curtirProjeto(Long idProjeto, Usuario usuario) throws ProjetoNotFoundException {
		Projeto p = retornarProjetoPorId(idProjeto);
		if (p.getCurtidoPorUsuarios().contains(usuario)) {
			p.getCurtidoPorUsuarios().remove(usuario);
		} else {
			p.getCurtidoPorUsuarios().add(usuario);
		}

		p.setNumCurtidas(p.getCurtidoPorUsuarios().size());

		projetoDAO.atualizarProjeto(p);
		return p.getNumCurtidas();
	}

	public int favoritarProjeto(Long idProjeto, Usuario usuario) throws ProjetoNotFoundException {
		Projeto p = retornarProjetoPorId(idProjeto);
		if (p.getFavoritadoPorUsuarios().contains(usuario)) {
			p.getFavoritadoPorUsuarios().remove(usuario);
		} else {
			p.getFavoritadoPorUsuarios().add(usuario);
		}

		p.setNumFavoritos(p.getFavoritadoPorUsuarios().size());

		projetoDAO.atualizarProjeto(p);
		return p.getNumFavoritos();
	}

	public void destacarProjeto(Long idProjeto) throws ProjetoNotFoundException {
		Projeto p = retornarProjetoPorId(idProjeto);
		p.setEmDestaque(true);
		projetoDAO.atualizarProjeto(p);
	}

}
