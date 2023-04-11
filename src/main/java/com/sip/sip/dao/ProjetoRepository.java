package com.sip.sip.dao;

import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto,Long>{
    List<Projeto> findByNumCurtidasGreaterThan(int min);

    List<Projeto> findByNumFavoritosGreaterThan(int min);

    List<Projeto> findByEmDesenvolvimentoTrue();

    List<Projeto> findByProcurandoVagasTrue();

    List<Projeto> findByDisponibilidadeHorasPorSemanaIs(int horasPorSemana);

    List<Projeto> findByDisponibilidadeDiasPorSemana(int diasPorSemana);

//    List<Projeto> findByTecnologias(List<Tecnologia> tecnologia);
//
//    List<Projeto> findByCargosDesejados(List<Cargo> cargos);

}
