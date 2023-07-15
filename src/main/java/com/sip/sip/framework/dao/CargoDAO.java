package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Cargo;
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
