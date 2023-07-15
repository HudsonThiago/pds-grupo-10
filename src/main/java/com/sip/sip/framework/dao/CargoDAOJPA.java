package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("CargoDAOJPA")
public class CargoDAOJPA implements CargoDAO{
    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public List<Cargo> listarCargos() {
        List<Cargo> cargos = new ArrayList<>();
        cargoRepository.findAll().forEach(cargos::add);
        return cargos;
    }

    @Override
    public Cargo buscarCargo(Long id) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);
        return cargoOptional.orElse(null);
    }

    @Override
    public Cargo criarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public Cargo atualizarCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public void excluirCargo(Long id) {
        cargoRepository.deleteById(id);
    }
}