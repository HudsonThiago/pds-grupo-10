package com.sip.sip.dao;

import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long>{
    List<Projeto> findByNumCurtidasGreaterThanEqualAndIdIn(int min,List<Long> ids);
    List<Projeto> findByNumCurtidasGreaterThanEqual(int min);

    List<Projeto> findByNumFavoritosGreaterThanEqualAndIdIn(int min, List<Long> ids);
    List<Projeto> findByNumFavoritosGreaterThanEqual(int min);

    List<Projeto> findByEmDesenvolvimentoAndIdIn(Boolean bool, List<Long> ids);
    List<Projeto> findByEmDesenvolvimento(Boolean bool);

    List<Projeto> findByProcurandoVagasAndIdIn(Boolean bool, List<Long> ids);
    List<Projeto> findByProcurandoVagas(Boolean bool);

    List<Projeto> findByDisponibilidadeHorasPorSemanaAndIdIn(int horasPorSemana, List<Long> ids);
    List<Projeto> findByDisponibilidadeHorasPorSemana(int horasPorSemana);

    List<Projeto> findByDisponibilidadeDiasPorSemanaAndIdIn(int diasPorSemana, List<Long> ids);
    List<Projeto> findByDisponibilidadeDiasPorSemana(int diasPorSemana);

    List<Projeto> findByTecnologiasIn(List<Tecnologia> tecnologias);
    List<Projeto> findByTecnologiasInAndIdIn(List<Tecnologia> tecnologias, List<Long> ids);
//
    List<Projeto> findByCargosDesejadosIn(List<Cargo> cargos);

    List<Projeto> findByCargosDesejadosInAndIdIn(List<Cargo> cargos, List<Long> ids);

}

