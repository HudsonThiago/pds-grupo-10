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

    public List<Tecnologia> listarTecnologias() {
        return tecnologiaDAO.listarTecnologias();
    }
}
