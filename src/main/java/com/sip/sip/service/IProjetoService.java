package com.sip.sip.service;

import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.dto.ProjetoDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Projeto;


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
}
