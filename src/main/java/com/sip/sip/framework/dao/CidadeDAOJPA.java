package com.sip.sip.framework.dao;

import com.sip.sip.framework.exception.CidadeNotFoundException;
import com.sip.sip.framework.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("CidadeDAOJPA")
public class CidadeDAOJPA implements CidadeDAO{

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<Cidade> listarCidades() {
        List<Cidade> cidades = new ArrayList<>();
        cidadeRepository.findAll().forEach(cidades::add);
        return cidades;
    }

    @Override
    public Cidade buscarCidade(Long id) {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
        return cidadeOptional.orElse(null);
    }

    @Override
    public Cidade criarCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    public Cidade atualizarCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    @Override
    public void excluirCidade(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public List<Cidade> listarCidadesParecidas(String cidadeQuery) {
        return cidadeRepository.findByNomeLikeIgnoreCase("%" + cidadeQuery + "%");
    }

    @Override
    public Cidade buscarCidadePorNome(String nome) throws CidadeNotFoundException {
        Cidade c = cidadeRepository.findByNome(nome);
        if(c == null) {
            throw new CidadeNotFoundException();
        } else {
            return c;
        }
    }
}