package com.sip.sip.framework.service;

import com.sip.sip.framework.dto.ProjetoCadastroDTO;
import com.sip.sip.framework.dto.ProjetoDTO;
import com.sip.sip.framework.exception.ProjetoNotFoundException;
import com.sip.sip.framework.model.Projeto;


import java.io.IOException;
import java.util.List;

public interface IProjetoService {
    ProjetoDTO buscarProjetoPorId(Long id) throws ProjetoNotFoundException;

    List<Projeto> listarProjetos();

    ProjetoDTO criarProjeto(ProjetoCadastroDTO projeto) throws IOException;

    ProjetoCadastroDTO atualizarProjeto(Long id, ProjetoCadastroDTO projeto) throws ProjetoNotFoundException, IOException;

    void excluirProjeto(Long id) throws ProjetoNotFoundException;

    Boolean ehMembro(Long idUsuario, Long idProjeto);

    ProjetoCadastroDTO buscarProjetoCadastradoPorId(Long id) throws ProjetoNotFoundException;

    void destacarProjeto(Long idProjeto) throws ProjetoNotFoundException;

    Projeto retornarProjetoPorId(Long id) throws ProjetoNotFoundException;
    void salvarProjeto(Projeto projeto); // testing purposes
}
