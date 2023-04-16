package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplorarProjetosService implements IExplorarProjetosService{
    @Qualifier("ProjetoDAOJPA")
    @Autowired
    private ProjetoDAO projetoDAO;
    @Qualifier("TecnologiaDAOJPA")
    @Autowired
    private TecnologiaDAO tecnologiaDAO;

    @Override
    public List<Projeto> listarProjetos() {
        return projetoDAO.listarProjetos();
    }

    public List<Projeto> filtrarProjetosNumCurtidasMaior (int min) {
//        return projetoDAO.filtrarProjetosNumCurtidasMaior(min);
        return null;
    }

    @Override
    public List<Projeto> filtrarProjetos(FiltroDTO filtros) {
        List<Projeto> projetos = projetoDAO.filtrarProjetos(filtros);
        return projetos;
    }
}
