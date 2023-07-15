package com.sip.sip.dao;

import com.sip.sip.model.Habilidade;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HabilidadeDAO {
    List<Habilidade> listarHabilidades();
    List<Habilidade> listarHabilidades(Boolean ativo);

    Habilidade buscarHabilidade(Long id);

    Habilidade criarHabilidade(Habilidade habilidade);

    Habilidade atualizarHabilidade(Habilidade habilidade);
    public List<Habilidade> listarTodasAsHabilidades();
    void excluirHabilidade(Long id);

    void mudarEstadoHabilidade(Long id);
}
