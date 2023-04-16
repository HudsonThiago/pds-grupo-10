package com.sip.sip.controller;

import com.sip.sip.exception.CargoNotFoundException;
import com.sip.sip.model.Cargo;
import com.sip.sip.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;



    @GetMapping("")
    public List<Cargo> listarCargos() {
        return cargoService.listarCargos();
    }

    @GetMapping("/{id}")
    public Cargo buscarCargoPorId(@PathVariable Long id) throws CargoNotFoundException {
        return cargoService.buscarCargoPorId(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Cargo criarCargo(@RequestBody Cargo cargo) {
        return cargoService.criarCargo(cargo);
    }

    @PutMapping("/{id}")
    public Cargo atualizarCargo(@PathVariable Long id, @RequestBody Cargo cargo) throws CargoNotFoundException {
        return cargoService.atualizarCargo(id, cargo);
    }

    @DeleteMapping("/{id}")
    public void excluirCargo(@PathVariable Long id) throws CargoNotFoundException {
        cargoService.excluirCargo(id);
    }
}