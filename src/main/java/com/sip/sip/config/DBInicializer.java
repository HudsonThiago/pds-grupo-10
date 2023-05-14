package com.sip.sip.config;

import com.sip.sip.dao.TecnologiaDAOJPA;
import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.MensagemCEnviadaDTO;
import com.sip.sip.dto.MensagemPEnviadaDTO;
import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.IMensagemCService;
import com.sip.sip.service.IMensagemPService;
import com.sip.sip.service.IProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DBInicializer implements CommandLineRunner {
    private TecnologiaDAOJPA tecnologia;
    private UsuarioDAOJPA usuario;

    @Autowired
    private IMensagemPService mensagemPService;

    @Autowired
    private IMensagemCService mensagemCService;
    @Autowired
    private IProjetoService projetoService;

    public DBInicializer(TecnologiaDAOJPA tecnologia, UsuarioDAOJPA usuario) {
        this.tecnologia = tecnologia;
        this.usuario = usuario;
    }

    @Override
    public void run(String... streings) throws Exception {
        instanciarTecnologias();
        instanciarUsuarios();
        instanciarMensagens();
        instanciarProjetos();
        instanciarMensagensChat();
    }

    private void instanciarTecnologias() {
        tecnologia.criarTecnologia(new Tecnologia(1l, "Java", true));
        tecnologia.criarTecnologia(new Tecnologia(2l, "React", true));
        tecnologia.criarTecnologia(new Tecnologia(3l, "C#", true));
        tecnologia.criarTecnologia(new Tecnologia(4l, "SpringBoot", true));
        tecnologia.criarTecnologia(new Tecnologia(5l, "Figma", true));
        tecnologia.criarTecnologia(new Tecnologia(6l, "Ilustrator", true));
    }

    private void instanciarUsuarios() {
        usuario.criarUsuario(new Usuario(1l, "Administrador", "adm@gmail.com", "123", true));
        usuario.criarUsuario(new Usuario(2l, "Usuario 1", "user1@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(3l, "Usuario 2", "user2@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(4l, "Usuario 3", "user3@gmail.com", "123"));
        usuario.criarUsuario(new Usuario(5l, "Usuario 4", "user4@gmail.com", "123"));
    }

    private void instanciarMensagens() throws IOException {
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user1 para o 2", 2l, 3l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user2 para o 1", 3l, 2l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user3 para o 1", 4l, 2l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user1 para o 3", 2l, 4l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user2 para o 3", 3l, 4l));

    }

    private void instanciarProjetos() throws IOException {
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto1","um projeto", 0,
                0,0, null,null));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto2","um projeto", 0,
                0,0, null,null));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto3","um projeto", 0,
                0,0, null,null));
    }
    private void instanciarMensagensChat() throws IOException, ProjetoNotFoundException {
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 2", 2l, 3l));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 1", 2l, 1l));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user3 para o projeto 1", 4l, 1l));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user2 para o projeto 1", 3l, 1l));
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 3", 2l, 4l));
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user2 para o projeto 3", 3l, 4l));

    }
}
