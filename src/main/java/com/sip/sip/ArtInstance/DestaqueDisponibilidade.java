package com.sip.sip.ArtInstance;

import com.sip.sip.framework.dao.ProjetoDAO;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.service.DestaqueStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class DestaqueDisponibilidade implements DestaqueStrategy  {

    @Autowired
    private ProjetoDAO projetoDAO;
    @Override
    public List<Projeto> listarProjetosDestacados() {
        List<Projeto> projetosDestaque = new ArrayList<Projeto>();
        List<Projeto> projetos = projetoDAO.listarProjetos();

        Collections.sort(projetos, new Comparator<Projeto>() {
            @Override
            public int compare(Projeto o1, Projeto o2) {
                return Integer.compare(o1.getDisponibilidade().getHorasPorSemana(), o2.getDisponibilidade().getHorasPorSemana());
            }
        });

        projetosDestaque.add(projetos.get(0));
        projetosDestaque.add(projetos.get(1));
        projetosDestaque.add(projetos.get(2));

        return projetosDestaque;
    }
}
