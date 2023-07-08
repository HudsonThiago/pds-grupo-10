package com.sip.sip.service;

import com.sip.sip.model.Projeto;
import com.sip.sip.model.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DestaqueStrategy {

    List<Projeto> listarProjetosDestacados();

}
