package com.sip.sip.config;

import com.sip.sip.dao.TecnologiaDAOJPA;
import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInicializer implements CommandLineRunner {
    private TecnologiaDAOJPA tecnologia;
    private UsuarioDAOJPA usuario;

    public DBInicializer(TecnologiaDAOJPA tecnologia, UsuarioDAOJPA usuario) {
        this.tecnologia = tecnologia;
        this.usuario = usuario;
    }

    @Override
    public void run(String... streings) throws Exception {
        instanciarTecnologias();
        instanciarUsuarios();
    }

    private void instanciarTecnologias(){
        tecnologia.criarTecnologia(new Tecnologia(1l, "Java", true));
        tecnologia.criarTecnologia(new Tecnologia(2l, "React", true));
        tecnologia.criarTecnologia(new Tecnologia(3l, "C#", true));
        tecnologia.criarTecnologia(new Tecnologia(4l, "SpringBoot", true));
        tecnologia.criarTecnologia(new Tecnologia(5l, "Figma", true));
        tecnologia.criarTecnologia(new Tecnologia(6l, "Ilustrator", true));
    }

    private void instanciarUsuarios(){
        usuario.criarUsuario(new Usuario(1l, "Administrador", "adm@gmail.com", "123", true));
        usuario.criarUsuario(new Usuario(2l, "Usuario 1", "user1@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(3l, "Usuario 2", "user2@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(4l, "Usuario 3", "user3@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(5l, "Usuario 4", "user4@gmail.com", "123"));
    }
}
