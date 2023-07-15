package com.sip.sip.framework.service;

import com.sip.sip.framework.dao.ProjetoDAO;
import com.sip.sip.framework.dto.FiltroDTO;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExplorarProjetosService implements IExplorarProjetosService{
    @Qualifier("ProjetoDAOJPA")
    @Autowired
    private ProjetoDAO projetoDAO;
    @Autowired
    private RecStrategy recStrategy;
    @Autowired
    private DestaqueStrategy destaqueStrategy;

    @Override
    public List<Projeto> listarProjetos() {
        return projetoDAO.listarProjetos();
    }


    @Override
    public List<Projeto> filtrarProjetos(FiltroDTO filtros) {
        List<Projeto> projetos = projetoDAO.filtrarProjetos(filtros);
        return projetos;
    }

    public List<Projeto> listarProjetosDestacados(){
        return destaqueStrategy.listarProjetosDestacados();
    }

    public List<Projeto> listarProjetosRecomendados(Usuario usuario) {
        return recStrategy.listarProjetosRecomendados(usuario);
    }
}
