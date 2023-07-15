package com.sip.sip.framework.dao;

import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.model.Cargo;
import com.sip.sip.framework.model.Cidade;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Habilidade;

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

	List<Projeto> filtrarProjetosHabilidades(List<Habilidade> habilidades);
	List<Projeto> filtrarProjetosHabilidades(List<Habilidade> habilidades, List<Long> ids);

	List<Projeto> filtrarProjetosCargos(List<Cargo> cargos);
	List<Projeto> filtrarProjetosCargos(List<Cargo> cargos, List<Long> ids);

	List<Projeto> filtrarProjetos(FiltroDTO filtros);

    List<Projeto> filtrarProjetosCidade(Cidade cidade);
}
