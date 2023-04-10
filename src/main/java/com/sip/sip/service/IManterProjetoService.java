package com.sip.sip.service;

import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;

import java.util.List;

public interface IManterProjetoService {
    Projeto buscarProjetoPorId(Long id) throws ProjetoNotFoundException;

    List<Projeto> listarProjetos();

    Projeto criarProjeto(Projeto projeto);

    Projeto atualizarProjeto(Long id, Projeto projeto) throws ProjetoNotFoundException;

    void excluirProjeto(Long id) throws ProjetoNotFoundException;
}
