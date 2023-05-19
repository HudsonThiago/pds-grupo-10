package com.sip.sip.service;

import com.sip.sip.model.Cargo;

import java.util.List;

public interface ICargoService {
    List<Cargo> listarCargos();

    Cargo criarCargo(Cargo cargo);

    Cargo buscarCargoPorId(Long id);

    Cargo atualizarCargo(Long id, Cargo cargo);

    void excluirCargo(Long id);
}
