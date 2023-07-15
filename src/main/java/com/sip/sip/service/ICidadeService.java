package com.sip.sip.service;

import com.sip.sip.exception.CidadeNotFoundException;
import com.sip.sip.model.Cidade;

import java.util.List;

public interface ICidadeService {
    Cidade buscarCidadePorId(Long id);

    List<Cidade> listarCidades();

    Cidade criarCidade(Cidade cidade);

    Cidade atualizarCidade(Cidade cidade);

    void excluirCidade(Long id);

    String listarCidadesParecidas(String cidadeQuery);

    Cidade buscarCidadePorNome(String nome) throws CidadeNotFoundException;
}
