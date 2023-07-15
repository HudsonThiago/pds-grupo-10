package com.sip.sip.framework.dto;

import org.springframework.data.util.Pair;

public class MensagemCDTO {
    private Long id;
    private String conteudo;
    private Pair<String,Long> usuarioRemetente;
    private Long projetoDestinatario;
    private String timestamp;
    private String arquivoURI = null;
    private String arquivoNome = null;

    public MensagemCDTO(String conteudo, Pair<String, Long> usuarioRemetente, Long projetoDestinatario, String timestamp) {
        this.conteudo = conteudo;
        this.usuarioRemetente = usuarioRemetente;
        this.projetoDestinatario = projetoDestinatario;
        this.timestamp = timestamp;
    }


    public static MensagemCDTO MensagemCDTO(String conteudo, Long usuarioRemetente, Long projetoDestinatarioId) {
        return new MensagemCDTO(conteudo, Pair.of(null, usuarioRemetente), projetoDestinatarioId, null);
    }

    public MensagemCDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Pair<String, Long> getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(Pair<String, Long> usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }

    public Long getProjetoDestinatario() {
        return projetoDestinatario;
    }

    public void setProjetoDestinatario(Long projetoDestinatario) {
        this.projetoDestinatario = projetoDestinatario;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getArquivoURI() {
        return this.arquivoURI;
    }

    public void setArquivoURI(String arquivoURI) {
        this.arquivoURI = arquivoURI;
    }

    public String getArquivoNome() {
        return this.arquivoNome;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }
}
