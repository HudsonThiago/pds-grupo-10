package com.sip.sip.service;

import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.model.Tecnologia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnologiaService implements ITecnologiaService{
    @Autowired
    @Qualifier("TecnologiaDAOJPA")
    private TecnologiaDAO tecnologiaDAO;

    public Tecnologia buscarTecnologiaPorId(Long id) {
        return tecnologiaDAO.buscarTecnologia(id);
    }

    public List<Tecnologia> listarTecnologias() {
        return tecnologiaDAO.listarTecnologias();
    }
    public List<Tecnologia> listarTecnologiasInativas() {
        return tecnologiaDAO.listarTecnologias(false);
    }

    public Tecnologia criarTecnologia(Tecnologia tecnologia) {
        return tecnologiaDAO.criarTecnologia(tecnologia);
    }

    public Tecnologia atualizarTecnologia(Long id, Tecnologia tecnologia) {
        return null;
    }

    public void excluirTecnologia(Long id) {

    }
}
