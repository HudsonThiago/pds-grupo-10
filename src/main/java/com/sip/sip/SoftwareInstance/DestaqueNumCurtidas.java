package com.sip.sip.SoftwareInstance;

import com.sip.sip.dao.ProjetoDAO;
import com.sip.sip.model.Projeto;
import com.sip.sip.service.DestaqueStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class DestaqueNumCurtidas implements DestaqueStrategy {

    @Autowired
    private ProjetoDAO projetoDAO;
    @Override
    public List<Projeto> listarProjetosDestacados() {
        List<Projeto> projetosDestaque = new ArrayList<Projeto>();
        List<Projeto> projetos = projetoDAO.listarProjetos();

        Collections.sort(projetos, new Comparator<Projeto>() {
            @Override
            public int compare(Projeto o1, Projeto o2) {
                return Integer.compare(o2.getNumCurtidas(), o1.getNumCurtidas());
            }
        });

        projetosDestaque.add(projetos.get(0));
        projetosDestaque.add(projetos.get(1));
        projetosDestaque.add(projetos.get(2));

        return projetosDestaque;
    }
}
