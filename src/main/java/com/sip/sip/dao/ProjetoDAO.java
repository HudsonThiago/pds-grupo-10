package com.sip.sip.dao;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.model.Tecnologia;

import java.util.List;

public interface ProjetoDAO {

	List<Projeto> listarProjetos();

	Projeto buscarProjetoPorId(Long id);

	Projeto criarProjeto(Projeto projeto);

	Projeto atualizarProjeto(Projeto projeto);

	void excluirProjeto(Long id);

	List<Projeto> filtrarProjetosNumCurtidasMaior (int min, List<Long> ids);
	List<Projeto> filtrarProjetosNumCurtidasMaior (int min);

	List<Projeto> filtrarProjetosNumFavoritosMaior(int min, List<Long> ids);
	List<Projeto> filtrarProjetosNumFavoritosMaior(int min);

	public List<Projeto> filtrarProjetosEmDesenvolvimento(Boolean bool, List<Long> ids);
	public List<Projeto> filtrarProjetosEmDesenvolvimento(Boolean bool);

	List<Projeto> filtrarProjetosProcurandoVagas(Boolean bool, List<Long> ids);
	List<Projeto> filtrarProjetosProcurandoVagas(Boolean bool);

	List<Projeto> filtrarProjetosDisponibilidadeHorasPorSemana(int horasPorSemana, List<Long> ids);
	List<Projeto> filtrarProjetosDisponibilidadeHorasPorSemana(int horasPorSemana);

	List<Projeto> filtrarProjetosDisponibilidadeDiasPorSemana(int diasPorSemana, List<Long> ids);
	List<Projeto> filtrarProjetosDisponibilidadeDiasPorSemana(int diasPorSemana);

	List<Projeto> filtrarProjetosTecnologias(List<Tecnologia> tecnologias);
	List<Projeto> filtrarProjetosTecnologias(List<Tecnologia> tecnologias, List<Long> ids);

	List<Projeto> filtrarProjetosCargos(List<Cargo> cargos);
	List<Projeto> filtrarProjetosCargos(List<Cargo> cargos, List<Long> ids);

	List<Projeto> filtrarProjetos(FiltroDTO filtros);

}
