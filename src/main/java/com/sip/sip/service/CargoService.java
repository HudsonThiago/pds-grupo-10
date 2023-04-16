package com.sip.sip.service;

import com.sip.sip.dao.CargoDAO;
import com.sip.sip.model.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService implements ICargoService{
    @Autowired
    @Qualifier("CargoDAOJPA")
    private CargoDAO cargoDAO;

    public Cargo buscarCargoPorId(Long id) {
        return cargoDAO.buscarCargo(id);
    }

    public List<Cargo> listarCargos() {
        return cargoDAO.listarCargos();
    }

    public Cargo criarCargo(Cargo cargo) {
        return cargoDAO.criarCargo(cargo);
    }

    public Cargo atualizarCargo(Long id, Cargo cargo) {
        return null;
    }

    public void excluirCargo(Long id) {

    }
}
