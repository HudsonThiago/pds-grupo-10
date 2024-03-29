package com.sip.sip.ArtInstance;

import com.sip.sip.framework.dao.ProjetoDAO;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Usuario;
import com.sip.sip.framework.service.RecStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public class HabilidadeRec implements RecStrategy {
//    @Qualifier("ProjetoDAOJPA")
//    @Autowired
    private ProjetoDAO projetoDAO;
    @Override
    public List<Projeto> listarProjetosRecomendados(Usuario usuario) {
        List<Projeto> projetosRec = projetoDAO.filtrarProjetosHabilidades(usuario.getHabilidades());

        int amountProjetos = Math.min(projetosRec.size(), 3);

        return projetosRec.subList(0,amountProjetos);
    }

    @Override
    public boolean getLocationRequired() {
        return false;
    }
}
