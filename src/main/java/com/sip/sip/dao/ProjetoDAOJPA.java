package com.sip.sip.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.sip.model.Projeto;

@Repository("ProjetoDAOJPA")
public class ProjetoDAOJPA implements ProjetoDAO {

	@Autowired
	private ProjetoRepository projetoRepository;
	@Override
	public List<Projeto> listarProjetos() {
		List<Projeto> projetos = new ArrayList<>();
		projetoRepository.findAll()
				.forEach(projetos::add);
		return projetos;
	}

	@Override
	public Projeto buscarProjetoPorId(Long id) {
		Optional<Projeto> projetoOptional = projetoRepository.findById(id);
		return projetoOptional.orElse(null);
	}

	@Override
	public Projeto criarProjeto(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	@Override
	public Projeto atualizarProjeto(Projeto projeto) {
		return projetoRepository.save(projeto);
	}

	@Override
	public void excluirProjeto(Long id) {
		projetoRepository.deleteById(id);
	}

}
