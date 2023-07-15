package com.sip.sip.framework.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



@Repository("ProjetoDAOJPA")
public class ProjetoDAOJPA implements ProjetoDAO {

	@Autowired
	private ProjetoRepository projetoRepository;
	@Autowired
	private HabilidadeDAO habilidadeDAO;
	@Autowired
	private CargoDAO cargoDAO;

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

	@Override
	public List<Projeto> filtrarProjetosNumCurtidasMaior (int min) {
		return projetoRepository.findByNumCurtidasGreaterThanEqual(min);
	}

	public List<Projeto> filtrarProjetosNumCurtidasMaior (int min, List<Long> ids) {
		return projetoRepository.findByNumCurtidasGreaterThanEqualAndIdIn(min, ids);
	}


	@Override
	public List<Projeto> filtrarProjetosNumFavoritosMaior(int min, List<Long> ids) {
		return projetoRepository.findByNumFavoritosGreaterThanEqualAndIdIn(min, ids);
	}

	public List<Projeto> filtrarProjetosNumFavoritosMaior(int min) {
		return projetoRepository.findByNumFavoritosGreaterThanEqual(min);
	}

	@Override
	public List<Projeto> filtrarProjetosEmDesenvolvimento(Boolean bool, List<Long> ids) {
		return projetoRepository.findByEmDesenvolvimentoAndIdIn(bool, ids);
	}

	public List<Projeto> filtrarProjetosEmDesenvolvimento(Boolean bool) {
		return projetoRepository.findByEmDesenvolvimento(bool);
	}

	@Override
	public List<Projeto> filtrarProjetosProcurandoVagas(Boolean bool, List<Long> ids) {
		return projetoRepository.findByProcurandoVagasAndIdIn(bool, ids);
	}

	public List<Projeto> filtrarProjetosProcurandoVagas(Boolean bool) {
		return projetoRepository.findByProcurandoVagas(bool);
	}

	@Override
	public List<Projeto> filtrarProjetosDisponibilidadeHorasPorSemana(int horasPorSemana, List<Long> ids) {
		return projetoRepository.findByDisponibilidadeHorasPorSemanaAndIdIn(horasPorSemana, ids);
	}

	public List<Projeto> filtrarProjetosDisponibilidadeHorasPorSemana(int horasPorSemana) {
		return projetoRepository.findByDisponibilidadeHorasPorSemana(horasPorSemana);
	}

	@Override
	public List<Projeto> filtrarProjetosDisponibilidadeDiasPorSemana(int diasPorSemana, List<Long> ids) {
		return projetoRepository.findByDisponibilidadeDiasPorSemanaAndIdIn(diasPorSemana, ids);
	}

	public List<Projeto> filtrarProjetosDisponibilidadeDiasPorSemana(int diasPorSemana) {
		return projetoRepository.findByDisponibilidadeDiasPorSemana(diasPorSemana);
	}

	@Override
	public List<Projeto> filtrarProjetosHabilidades(List<Habilidade> habilidades) {
		return projetoRepository.findByHabilidadesIn(habilidades);
	}
	public List<Projeto> filtrarProjetosHabilidades(List<Habilidade> habilidades, List<Long> ids) {
		return projetoRepository.findByHabilidadesInAndIdIn(habilidades, ids);
	}

	@Override
	public List<Projeto> filtrarProjetosCargos(List<Cargo> cargos) {
		return projetoRepository.findByCargosDesejadosIn(cargos);
	}
	public List<Projeto> filtrarProjetosCargos(List<Cargo> cargos, List<Long> ids) {
		return projetoRepository.findByCargosDesejadosInAndIdIn(cargos, ids);
	}

    @Override
    public List<Projeto> filtrarProjetos(FiltroDTO filtros) {
		List<Projeto> projetos = listarProjetos();
		int minCurtidas = filtros.getMinCurtidas();
		int minFavoritos = filtros.getMinFavoritos();
		int horasPorSemana = filtros.getHorasPorSemana();
		int diasPorSemana = filtros.getDiasPorSemana();
		String procurandoVagas = filtros.getProcurandoVagas();
		String emDesenvolvimento = filtros.getEmDesenvolvimento();
		List<Long> habilidadesId = filtros.getHabilidadesEscolhidasId();
		List<Habilidade> habilidades = habilidadesId.stream().map(habilidadeId -> habilidadeDAO.buscarHabilidade(habilidadeId))
								.collect(Collectors.toList());
		List<Long> cargosId = filtros.getCargosEscolhidosId();
		List<Cargo> cargos = cargosId.stream().map(cargoId -> cargoDAO.buscarCargo(cargoId)).collect(Collectors.toList());

		List<Long> ids = null;

		if(minCurtidas != 0) {
			if (ids == null) {
				projetos = filtrarProjetosNumCurtidasMaior(minCurtidas);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosNumCurtidasMaior(minCurtidas,ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if(minFavoritos != 0 ) {
			if (ids == null) {
				projetos = filtrarProjetosNumFavoritosMaior(minFavoritos);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosNumFavoritosMaior(minFavoritos,ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if(horasPorSemana != 0) {
			if (ids == null) {
				projetos = filtrarProjetosDisponibilidadeHorasPorSemana(horasPorSemana);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosDisponibilidadeHorasPorSemana(minCurtidas,ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if(diasPorSemana != 0) {
			if (ids == null) {
				projetos = filtrarProjetosDisponibilidadeDiasPorSemana(diasPorSemana);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosDisponibilidadeDiasPorSemana(diasPorSemana,ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if(procurandoVagas.equals("checked")) {
			if (ids == null) {
				projetos = filtrarProjetosProcurandoVagas(true);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosProcurandoVagas(true, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		} else if (procurandoVagas.equals("indeterminate")) {
			if (ids == null) {
				projetos = filtrarProjetosProcurandoVagas(false);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosProcurandoVagas(false, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if(emDesenvolvimento.equals("checked")) {
			if (ids == null) {
				projetos = filtrarProjetosEmDesenvolvimento(true);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosEmDesenvolvimento(true, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		} else if (emDesenvolvimento.equals("indeterminate")) {
			if (ids == null) {
				projetos = filtrarProjetosEmDesenvolvimento(false);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			} else {
				projetos = filtrarProjetosEmDesenvolvimento(false, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}

		if (!habilidades.isEmpty()) {
			if (ids == null) {
				projetos = filtrarProjetosHabilidades(habilidades);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
			else {
				projetos = filtrarProjetosHabilidades(habilidades, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		if (!cargos.isEmpty()) {
			if (ids == null) {
				projetos = filtrarProjetosCargos(cargos);
				if (projetos.isEmpty()) return projetos;

				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
			else {
				projetos = filtrarProjetosCargos(cargos, ids);
				ids = projetos.stream().map(Projeto::getId).collect(Collectors.toList());
			}
		}
		return projetos;
    }

	@Override
	public List<Projeto> filtrarProjetosCidade(Cidade cidade) {
		return projetoRepository.findByCidade(cidade);
	}

}
