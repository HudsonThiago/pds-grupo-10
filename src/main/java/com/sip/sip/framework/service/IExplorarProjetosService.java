package com.sip.sip.framework.service;

import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Usuario;

import java.util.List;

public interface IExplorarProjetosService {
    List<Projeto> listarProjetos();

    List<Projeto> filtrarProjetos(FiltroDTO filtros);

    List<Projeto> listarProjetosDestacados();

    List<Projeto> listarProjetosRecomendados(Usuario usuario);
}
