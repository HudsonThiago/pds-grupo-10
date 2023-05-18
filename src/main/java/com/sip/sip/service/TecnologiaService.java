package com.sip.sip.service;

import com.sip.sip.dao.TecnologiaDAO;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.BeanUtils;
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
    public List<Tecnologia> listarTodasAsTecnologias() {
        return tecnologiaDAO.listarTodasAsTecnologias();
    }

    public List<Tecnologia> listarTecnologiasInativas() {
        return tecnologiaDAO.listarTecnologias(false);
    }

    public Tecnologia criarTecnologia(Tecnologia tecnologia) {
        return tecnologiaDAO.criarTecnologia(tecnologia);
    }

    public void solicitarTecnologia(Tecnologia tecnologia) {

        UsuarioLogado usuarioLogado = new UsuarioLogado();
        tecnologia.setAtivo(false);
        tecnologia.setIdUsuarioQueSolicitou(usuarioLogado.id);

        tecnologiaDAO.criarTecnologia(tecnologia);
    }

    public void mudarEstadoTecnologia(Long id){
        tecnologiaDAO.mudarEstadoTecnologia(id);
    }

    public Tecnologia atualizarTecnologia(Long id, Tecnologia tecnologia) {
        return null;
    }

    public void excluirTecnologia(Long id) {
        tecnologiaDAO.excluirTecnologia(id);
    }
}
