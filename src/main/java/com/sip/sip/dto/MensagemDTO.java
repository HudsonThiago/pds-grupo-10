package com.sip.sip.dto;

import org.springframework.data.util.Pair;

public class MensagemDTO {
    private Long id;
    private String titulo;
    private String conteudo;
    private Pair<String,Long> usuarioRemetente;
    private Pair<String,Long> usuarioDestinatario;
    private String timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Pair<String, Long> getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Pair<String, Long> usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
