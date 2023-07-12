package com.sip.sip.service;

import com.sip.sip.dao.HabilidadeDAO;
import com.sip.sip.model.Habilidade;
import com.sip.sip.model.Usuario;
import com.sip.sip.model.usuarioLogado.UsuarioLogado;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService implements IHabilidadeService{
    @Autowired
    @Qualifier("HabilidadeDAOJPA")
    private HabilidadeDAO habilidadeDAO;

    public Habilidade buscarHabilidadePorId(Long id) {
        return habilidadeDAO.buscarHabilidade(id);
    }

    public List<Habilidade> listarHabilidades() {
        return habilidadeDAO.listarHabilidades();
    }
    public List<Habilidade> listarTodasAsHabilidades() {
        return habilidadeDAO.listarTodasAsHabilidades();
    }

    public List<Habilidade> listarHabilidadesInativas() {
        return habilidadeDAO.listarHabilidades(false);
    }

    public Habilidade criarHabilidade(Habilidade habilidade) {
        return habilidadeDAO.criarHabilidade(habilidade);
    }

    public void solicitarHabilidade(Habilidade habilidade) {

        UsuarioLogado usuarioLogado = new UsuarioLogado();
        habilidade.setAtivo(false);
        habilidade.setIdUsuarioQueSolicitou(usuarioLogado.id);

        habilidadeDAO.criarHabilidade(habilidade);
    }

    public void mudarEstadoHabilidade(Long id){
        habilidadeDAO.mudarEstadoHabilidade(id);
    }

    public Habilidade atualizarHabilidade(Long id, Habilidade habilidade) {
        return null;
    }

    public void excluirHabilidade(Long id) {
        habilidadeDAO.excluirHabilidade(id);
    }
}
