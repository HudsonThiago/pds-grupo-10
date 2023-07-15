package com.sip.sip.SoftwareInstance;

import com.sip.sip.framework.dao.ProjetoDAO;
import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Usuario;
import com.sip.sip.framework.service.RecStrategy;

import java.util.List;

//@Component
public class CargoRec implements RecStrategy {
//    @Qualifier("ProjetoDAOJPA")
//    @Autowired
    private ProjetoDAO projetoDAO;
    @Override
    public List<Projeto> listarProjetosRecomendados(Usuario usuario) {
        List<Projeto> projetosRec = projetoDAO.filtrarProjetosCargos(usuario.getCargos());

        int amountProjetos = Math.min(projetosRec.size(), 3);

        return projetosRec.subList(0,amountProjetos);
    }

    @Override
    public boolean getLocationRequired() {
        return false;
    }
}
