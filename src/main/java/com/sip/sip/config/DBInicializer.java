package com.sip.sip.config;

import com.sip.sip.dao.TecnologiaDAOJPA;
import com.sip.sip.dao.UsuarioDAOJPA;
import com.sip.sip.dto.MensagemCEnviadaDTO;
import com.sip.sip.dto.MensagemPEnviadaDTO;
import com.sip.sip.dto.ProjetoCadastroDTO;
import com.sip.sip.exception.ProjetoNotFoundException;
import com.sip.sip.model.Cargo;
import com.sip.sip.model.Projeto;
import com.sip.sip.model.Tecnologia;
import com.sip.sip.model.Usuario;
import com.sip.sip.service.ICargoService;
import com.sip.sip.service.IMensagemCService;
import com.sip.sip.service.IMensagemPService;
import com.sip.sip.service.IProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private ICargoService cargoService;
    public DBInicializer(TecnologiaDAOJPA tecnologia, UsuarioDAOJPA usuario) {
        this.tecnologia = tecnologia;
        this.usuario = usuario;
    }

    @Override
    public void run(String... streings) throws Exception {
        instanciarTecnologias();
        instanciarCargos();
        instanciarUsuarios();
        instanciarMensagens();
        instanciarProjetos();
        instanciarMensagensChat();
    }

    private void instanciarCargos() {
        cargoService.criarCargo(new Cargo("Desenvolvedor Frontend"));
        cargoService.criarCargo(new Cargo("Desenvolvedor Backend"));
        cargoService.criarCargo(new Cargo("Designer"));
        cargoService.criarCargo(new Cargo("Testador"));

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
        Usuario u1 = usuario.buscarUsuarioPorId(2l);
        u1.setCargos(List.of(cargoService.buscarCargoPorId(1l)));
        usuario.atualizarUsuario(u1);
        usuario.criarUsuario(new Usuario(3l, "Usuario 2", "user2@gmail.com", "123"));
        Usuario u2 = usuario.buscarUsuarioPorId(3l);
        u2.setCargos(List.of(cargoService.buscarCargoPorId(2l)));
        usuario.atualizarUsuario(u2);
        usuario.criarUsuario(new Usuario(4l, "Usuario 3", "user3@gmail.com", "123"));
        Usuario u3 = usuario.buscarUsuarioPorId(4l);
        u3.setCargos(List.of(cargoService.buscarCargoPorId(3l)));
        usuario.atualizarUsuario(u3);
        usuario.criarUsuario(new Usuario(5l, "Usuario 4", "user4@gmail.com", "123"));
        Usuario u4 = usuario.buscarUsuarioPorId(5l);
        u4.setCargos(List.of(cargoService.buscarCargoPorId(4l)));
        usuario.atualizarUsuario(u4);
    }

    private void instanciarMensagens() throws IOException {
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user1 para o 2", 2l, 3l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user2 para o 1", 3l, 2l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user3 para o 1", 4l, 2l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user1 para o 3", 2l, 4l));
        mensagemPService.criarMensagem(new MensagemPEnviadaDTO("teste do user2 para o 3", 3l, 4l));

    }

    private void instanciarProjetos() throws IOException, ProjetoNotFoundException {
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto1","um projeto", 20,
                5,0,40, List.of(1l,2l),List.of(1l,2l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto2","um projeto", 14,
                4,0,121,  List.of(2l,3l),List.of(2l,3l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto3","um projeto", 32,
                5,0,74,  List.of(3l,1l,2l),List.of(1l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto4","um projeto", 14,
                3,0,33,  List.of(1l,2l),List.of(1l, 4l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto5","um projeto", 40,
                5,0,56,  List.of(2l),List.of(3l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto6","um projeto", 12,
                3,0,49,  List.of(1l,4l),List.of(3l,4l)));

        Projeto p1 = projetoService.retornarProjetoPorId(1l);
        p1.setMembros(List.of(usuario.buscarUsuarioPorId(4l), usuario.buscarUsuarioPorId(2l), usuario.buscarUsuarioPorId(3l)));
        projetoService.salvarProjeto(p1);

        Projeto p2 = projetoService.retornarProjetoPorId(2l);
        p1.setMembros(List.of(usuario.buscarUsuarioPorId(4l), usuario.buscarUsuarioPorId(2l)));
        projetoService.salvarProjeto(p2);

        Projeto p3 = projetoService.retornarProjetoPorId(3l);
        p1.setMembros(List.of(usuario.buscarUsuarioPorId(4l)));
        projetoService.salvarProjeto(p3);
    }
    private void instanciarMensagensChat() throws IOException, ProjetoNotFoundException {
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 2", 2l, 3l));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 1", 2l, 1l,null));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user3 para o projeto 1", 4l, 1l,null));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user2 para o projeto 1", 3l, 1l,null));
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 3", 2l, 4l));
//        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user2 para o projeto 3", 3l, 4l));

    }
}
