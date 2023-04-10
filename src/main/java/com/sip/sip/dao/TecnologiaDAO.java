package com.sip.sip.dao;

import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TecnologiaDAO {
    List<Tecnologia> listarTecnologias();

    Tecnologia buscarTecnologiaPorId(Long id);

    Tecnologia criarTecnologia(Tecnologia tecnologia);

    Tecnologia atualizarTecnologia(Tecnologia tecnologia);

    void excluirTecnologia(Long id);
}
