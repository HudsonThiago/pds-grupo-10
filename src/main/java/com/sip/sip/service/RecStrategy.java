package com.sip.sip.service;

import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;

import java.util.List;

public interface RecStrategy {

    List<Projeto> listarProjetosRecomendados(Usuario usuario);
}
