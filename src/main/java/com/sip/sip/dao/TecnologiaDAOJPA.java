package com.sip.sip.dao;

import com.sip.sip.model.Tecnologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("TecnologiaDAOJPA")
public class TecnologiaDAOJPA implements TecnologiaDAO{

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Override
    public List<Tecnologia> listarTodasAsTecnologias() {
        List<Tecnologia> tecnologias = new ArrayList<>();
        tecnologiaRepository.findAll().forEach(tecnologias::add);
        return tecnologias;
    }
    @Override
    public List<Tecnologia> listarTecnologias() {
        return tecnologiaRepository.findByAtivoEquals(true);
    }

    @Override
    public List<Tecnologia> listarTecnologias(Boolean ativo) {
        return tecnologiaRepository.findByAtivoEquals(ativo);
    }

    @Override
    public Tecnologia buscarTecnologia(Long id) {
        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findById(id);
        return tecnologiaOptional.orElse(null);
    }

    @Override
    public Tecnologia criarTecnologia(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }

    @Override
    public Tecnologia atualizarTecnologia(Tecnologia tecnologia) {
        return tecnologiaRepository.save(tecnologia);
    }

    @Override
    public void excluirTecnologia(Long id) {
        tecnologiaRepository.deleteById(id);
    }

    @Override
    public void mudarEstadoTecnologia(Long id){
        Optional<Tecnologia> tecnologiaOptional = tecnologiaRepository.findById(id);
        tecnologiaOptional.get().setAtivo(!(tecnologiaOptional.get().getAtivo()));
        tecnologiaRepository.save(tecnologiaOptional.get());
    };
}