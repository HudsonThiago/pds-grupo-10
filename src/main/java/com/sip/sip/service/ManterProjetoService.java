package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManterProjetoService implements IManterProjetoService {
	
	@Autowired
	@Qualifier("ProjectDAOJPA")
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

	public Projeto criarProjeto(Projeto projeto) {
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
