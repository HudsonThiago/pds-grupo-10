package com.sip.sip.service;

import com.google.gson.Gson;
import com.sip.sip.dao.CidadeDAO;
import com.sip.sip.exception.CidadeNotFoundException;
import com.sip.sip.model.Cidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService implements ICidadeService{
    @Autowired
    @Qualifier("CidadeDAOJPA")
    private CidadeDAO cidadeDAO;

    @Override
    public Cidade buscarCidadePorId(Long id) {
        return cidadeDAO.buscarCidade(id);
    }

    @Override
    public List<Cidade> listarCidades() {
        return cidadeDAO.listarCidades();
    }

    @Override
    public Cidade criarCidade(Cidade cidade) {
        return cidadeDAO.criarCidade(cidade);
    }

    @Override
    public Cidade atualizarCidade(Cidade cidade) {
        return cidadeDAO.atualizarCidade(cidade);
    }

    @Override
    public void excluirCidade(Long id) {
        cidadeDAO.excluirCidade(id);
    }

    @Override
    public String listarCidadesParecidas(String cidadeQuery) {
        Gson gson = new Gson();
        List<String> cidadesNomes = cidadeDAO.listarCidadesParecidas(cidadeQuery).stream()
                .map(Cidade::getNome)
                .collect(Collectors.toList());
        return gson.toJson(cidadesNomes);
    }

    @Override
    public Cidade buscarCidadePorNome(String nome) throws CidadeNotFoundException {
        return cidadeDAO.buscarCidadePorNome(nome);
    }
}
