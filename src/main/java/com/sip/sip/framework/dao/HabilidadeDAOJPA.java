package com.sip.sip.framework.dao;

import com.sip.sip.framework.model.Habilidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("HabilidadeDAOJPA")
public class HabilidadeDAOJPA implements HabilidadeDAO{

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Override
    public List<Habilidade> listarTodasAsHabilidades() {
        List<Habilidade> habilidades = new ArrayList<>();
        habilidadeRepository.findAll().forEach(habilidades::add);
        return habilidades;
    }
    @Override
    public List<Habilidade> listarHabilidades() {
        return habilidadeRepository.findByAtivoEquals(true);
    }

    @Override
    public List<Habilidade> listarHabilidades(Boolean ativo) {
        return habilidadeRepository.findByAtivoEquals(ativo);
    }

    @Override
    public Habilidade buscarHabilidade(Long id) {
        Optional<Habilidade> habilidadeOptional = habilidadeRepository.findById(id);
        return habilidadeOptional.orElse(null);
    }

    @Override
    public Habilidade criarHabilidade(Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    @Override
    public Habilidade atualizarHabilidade(Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    @Override
    public void excluirHabilidade(Long id) {
        habilidadeRepository.deleteById(id);
    }

    @Override
    public void mudarEstadoHabilidade(Long id){
        Optional<Habilidade> habilidadeOptional = habilidadeRepository.findById(id);
        habilidadeOptional.get().setAtivo(!(habilidadeOptional.get().getAtivo()));
        habilidadeRepository.save(habilidadeOptional.get());
    };
}