package com.sip.sip.service;

import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;


import java.io.IOException;
import java.util.List;

public interface IProjetoService {
    Projeto buscarProjetoPorId(Long id) throws ProjetoNotFoundException;

    List<Projeto> listarProjetos();

    ProjetoDTO criarProjeto(ProjetoCadastroDTO projeto) throws IOException;

    Projeto atualizarProjeto(Long id, Projeto projeto) throws ProjetoNotFoundException;

    void excluirProjeto(Long id) throws ProjetoNotFoundException;
}
