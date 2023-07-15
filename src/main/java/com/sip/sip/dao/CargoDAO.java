package com.sip.sip.dao;

import com.sip.sip.model.Cargo;
import com.sip.sip.model.Habilidade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoDAO {
    List<Cargo> listarCargos();

    Cargo buscarCargo(Long id);

    Cargo criarCargo(Cargo cargo);

    Cargo atualizarCargo(Cargo cargo);

    void excluirCargo(Long id);
}
