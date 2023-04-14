package com.sip.sip.service;

import com.sip.sip.dao.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManterUsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

}
