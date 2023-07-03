package com.sip.sip.service;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.dto.FiltroDTO;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExplorarProjetosService implements IExplorarProjetosService, DestaqueStrategy{
    @Qualifier("ProjetoDAOJPA")
    @Autowired
    private ProjetoDAO projetoDAO;
    @Autowired
    private RecStrategy recStrategy;

    @Override
    public List<Projeto> listarProjetos() {
        return projetoDAO.listarProjetos();
    }


    @Override
    public List<Projeto> filtrarProjetos(FiltroDTO filtros) {
        List<Projeto> projetos = projetoDAO.filtrarProjetos(filtros);
        return projetos;
    }

    @Override
    public List<Projeto> listarProjetosDestacados(){
        //APLICAÇÃO 1: DESTAQUE MANUAL
        List<Projeto> projetosDestaque = new ArrayList<Projeto>();
        Projeto projeto1 = projetoDAO.buscarProjetoPorId(4l);
        Projeto projeto2 = projetoDAO.buscarProjetoPorId(6l);
        Projeto projeto3 = projetoDAO.buscarProjetoPorId(5l);

        projetosDestaque.add(projeto1);
        projetosDestaque.add(projeto2);
        projetosDestaque.add(projeto3);

        return projetosDestaque;

        //APLICAÇÃO 2: DESTAQUE POR CURTIDAS
        //APLICAÇÃO 3: DESTAQUE
    }

    public List<Projeto> listarProjetosRecomendados(Usuario usuario) {
        return recStrategy.listarProjetosRecomendados(usuario);
    }
}
