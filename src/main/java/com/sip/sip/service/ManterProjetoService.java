package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Disponibilidade;
import com.sip.sip.model.Projeto;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.model.Tecnologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManterProjetoService implements IManterProjetoService {
	
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

	public Projeto criarProjeto(ProjetoDTO dto) {
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

		return projetoDAO.criarProjeto(projeto);
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
