package com.sip.sip.dao;

import com.sip.sip.model.Tecnologia;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TecnologiaDAO {
    List<Tecnologia> listarTecnologias();
    List<Tecnologia> listarTecnologias(Boolean ativo);

    Tecnologia buscarTecnologia(Long id);

    Tecnologia criarTecnologia(Tecnologia tecnologia);

    Tecnologia atualizarTecnologia(Tecnologia tecnologia);
    public List<Tecnologia> listarTodasAsTecnologias();
    void excluirTecnologia(Long id);

    void mudarEstadoTecnologia(Long id);
}
