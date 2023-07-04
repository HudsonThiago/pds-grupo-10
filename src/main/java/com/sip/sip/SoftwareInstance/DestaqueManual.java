package com.sip.sip.SoftwareInstance;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.model.Projeto;
import com.sip.sip.service.DestaqueStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DestaqueManual implements DestaqueStrategy {

    @Qualifier("ProjetoDAOJPA")
    @Autowired
    private ProjetoDAO projetoDAO;
    @Override
    public List<Projeto> listarProjetosDestacados() {
        List<Projeto> projetosDestaque = new ArrayList<Projeto>();
        Projeto projeto1 = projetoDAO.buscarProjetoPorId(4l);
        Projeto projeto2 = projetoDAO.buscarProjetoPorId(6l);
        Projeto projeto3 = projetoDAO.buscarProjetoPorId(5l);

        projetosDestaque.add(projeto1);
        projetosDestaque.add(projeto2);
        projetosDestaque.add(projeto3);

        return projetosDestaque;
    }
}
