package com.sip.sip.service;

import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Projeto;

import java.util.List;

public interface IExplorarProjetosService {
    List<Projeto> listarProjetos();

    List<Projeto> filtrarProjetosNumCurtidasMaior (int min);

    List<Projeto> filtrarProjetos(FiltroDTO filtros);
}
