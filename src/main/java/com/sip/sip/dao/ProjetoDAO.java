package com.sip.sip.dao;

import com.sip.sip.model.Projeto;

import java.util.List;

public interface ProjetoDAO {

	List<Projeto> listarProjetos();

	Projeto buscarProjetoPorId(Long id);

	Projeto criarProjeto(Projeto projeto);

	Projeto atualizarProjeto(Projeto projeto);

	void excluirProjeto(Long id);

	List<Projeto> filtrarProjetosNumCurtidasMaior (int min);

}
