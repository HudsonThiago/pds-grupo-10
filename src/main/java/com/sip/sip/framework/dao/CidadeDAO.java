package com.sip.sip.framework.dao;

import com.sip.sip.framework.exception.CidadeNotFoundException;
import com.sip.sip.framework.model.Cidade;

import java.util.List;

public interface CidadeDAO {
    List<Cidade> listarCidades();

    Cidade buscarCidade(Long id);

    Cidade criarCidade(Cidade cidade);

    Cidade atualizarCidade(Cidade cidade);

    void excluirCidade(Long id);

    List<Cidade> listarCidadesParecidas(String cidadeQuery);

    Cidade buscarCidadePorNome(String nome) throws CidadeNotFoundException;
}
