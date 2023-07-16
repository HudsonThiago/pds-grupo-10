package com.sip.sip.framework.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sip.sip.framework.dao.HabilidadeDAOJPA;
import com.sip.sip.framework.dao.UsuarioDAOJPA;
import com.sip.sip.framework.dto.MensagemCEnviadaDTO;
import com.sip.sip.framework.dto.MensagemPEnviadaDTO;
import com.sip.sip.framework.dto.ProjetoCadastroDTO;
import com.sip.sip.framework.exception.CidadeNotFoundException;
import com.sip.sip.framework.exception.ProjetoNotFoundException;
import com.sip.sip.framework.model.*;
import com.sip.sip.framework.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBInicializer implements CommandLineRunner {
    private HabilidadeDAOJPA habilidade;
    private UsuarioDAOJPA usuario;

    @Autowired
    private IMensagemPService mensagemPService;

    @Autowired
    private IMensagemCService mensagemCService;
    @Autowired
    private IProjetoService projetoService;
    @Autowired
    private ICargoService cargoService;
    @Autowired
    private ICidadeService cidadeService;

    public DBInicializer(HabilidadeDAOJPA habilidade, UsuarioDAOJPA usuario) {
        this.habilidade = habilidade;
        this.usuario = usuario;
    }

    @Override
    public void run(String... streings) throws Exception {
        instanciarHabilidades();
        instanciarCidades();
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

    private void instanciarHabilidades() {
        habilidade.criarHabilidade(new Habilidade(1l, "Java", true));
        habilidade.criarHabilidade(new Habilidade(2l, "React", true));
        habilidade.criarHabilidade(new Habilidade(3l, "C#", true));
        habilidade.criarHabilidade(new Habilidade(4l, "SpringBoot", true));
        habilidade.criarHabilidade(new Habilidade(5l, "Figma", true));
        habilidade.criarHabilidade(new Habilidade(6l, "Ilustrator", true));
    }

    private void instanciarUsuarios() {
        usuario.criarUsuario(new Usuario(1l, "Administrador", "adm@gmail.com", "123", true));
        usuario.criarUsuario(new Usuario(2l, "Usuario 1", "user1@gmail.com", "123"));
        Usuario u1 = usuario.buscarUsuarioPorId(2l);
        u1.setCargos(List.of(cargoService.buscarCargoPorId(1l)));
        Cidade natal = null;
        try {
            natal = cidadeService.buscarCidadePorNome("Natal, Rio Grande do Norte");
        } catch (CidadeNotFoundException e) {
            throw new RuntimeException(e);
        }
        u1.setCidade(natal);
        u1.setHabilidades(List.of(habilidade.buscarHabilidade(1l), habilidade.buscarHabilidade(2l)));
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
        String descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean nisl ipsum, faucibus sed " +
                "consequat quis, aliquet id nisi. Cras diam velit, iaculis ac rutrum a, ultricies id mi.";
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 1",descricao, 20,
                5,0,40, List.of(1l,2l),List.of(1l,2l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 2",descricao, 14,
                4,0,121,  List.of(2l,3l),List.of(2l,3l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 3",descricao, 32,
                5,0,74,  List.of(3l,1l,2l),List.of(1l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 4",descricao, 14,
                3,0,33,  List.of(1l,2l),List.of(1l, 4l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 5",descricao, 40,
                5,0,56,  List.of(2l),List.of(3l)));
        projetoService.criarProjeto(new ProjetoCadastroDTO("projeto 6",descricao, 12,
                3,0,49,  List.of(1l,4l),List.of(3l,4l)));

        Cidade natal = null;
        Cidade fortaleza = null;
        try {
            natal = cidadeService.buscarCidadePorNome("Natal, Rio Grande do Norte");
            fortaleza = cidadeService.buscarCidadePorNome("Fortaleza, Ceará");

        } catch (CidadeNotFoundException e) {
            throw new RuntimeException(e);
        }

        Projeto p1 = projetoService.retornarProjetoPorId(1L);
        Projeto p2 = projetoService.retornarProjetoPorId(2L);
        Projeto p3 = projetoService.retornarProjetoPorId(3L);
        Projeto p4 = projetoService.retornarProjetoPorId(4L);
        Usuario usuario4 = usuario.buscarUsuarioPorId(4L);
        Usuario usuario2 = usuario.buscarUsuarioPorId(2L);
        Usuario usuario3 = usuario.buscarUsuarioPorId(3L);

        UsuarioProjeto up41 = new UsuarioProjeto();
        up41.setUsuario(usuario4);
        up41.setProjeto(p1);
        UsuarioProjeto up21 = new UsuarioProjeto();
        up21.setUsuario(usuario2);
        up21.setProjeto(p1);
        UsuarioProjeto up31 = new UsuarioProjeto();
        up31.setUsuario(usuario3);
        up31.setProjeto(p1);

        p1.setUsuariosProjeto(List.of(up41, up21, up31));
        p1.setCidade(natal);
        p1.setHabilidades(List.of(habilidade.buscarHabilidade(1l), habilidade.buscarHabilidade(2l)));
        projetoService.salvarProjeto(p1);



        UsuarioProjeto up32 = new UsuarioProjeto();
        up32.setUsuario(usuario3);
        up32.setProjeto(p2);
        p2.setUsuariosProjeto(List.of(up32));
        p2.setCidade(natal);
        p2.setHabilidades(List.of(habilidade.buscarHabilidade(1l), habilidade.buscarHabilidade(4l)));
        projetoService.salvarProjeto(p2);


        UsuarioProjeto up23 = new UsuarioProjeto();
        up23.setUsuario(usuario2);
        up23.setProjeto(p3);
        p3.setUsuariosProjeto(List.of(up23));
        p3.setCidade(fortaleza);
        p3.setHabilidades(List.of(habilidade.buscarHabilidade(3l), habilidade.buscarHabilidade(6l)));
        projetoService.salvarProjeto(p3);

        p4.setCidade(natal);
        p4.setHabilidades(List.of(habilidade.buscarHabilidade(3l)));
        projetoService.salvarProjeto(p4);


    }
    private void instanciarMensagensChat() throws IOException, ProjetoNotFoundException {
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user1 para o projeto 1", 2l, 1l,null));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user3 para o projeto 1", 4l, 1l,null));
        mensagemCService.criarMensagem(new MensagemCEnviadaDTO("teste do user2 para o projeto 1", 3l, 1l,null));


    }

    private void instanciarCidades() {
        String jsonFile = "src/main/resources/static/cidades.json";
        String jsonString = lerArquivoCidades(jsonFile);

        // Convert the JSON array to a list of strings using Gson
        Gson gson = new Gson();
        List<String> nomes = gson.fromJson(jsonString, new TypeToken<List<String>>() {}.getType());

        // Convert each string to a Cidade entity using criarCidade
        List<Cidade> cidades = nomes.stream()
                .map(Cidade::new)
                .collect(Collectors.toList());
        cidades.forEach(cidadeService::criarCidade);
    }
    private static String lerArquivoCidades(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
