package com.sip.sip.framework.service;

import com.sip.sip.framework.model.Projeto;
import com.sip.sip.framework.model.Usuario;

import java.util.List;

public interface RecStrategy {

    List<Projeto> listarProjetosRecomendados(Usuario usuario);

    boolean getLocationRequired();
}
