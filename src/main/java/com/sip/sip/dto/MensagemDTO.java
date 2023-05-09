package com.sip.sip.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.util.Pair;

public class MensagemDTO {
    private Long id;
    private String conteudo;
    private Pair<String,Long> usuarioRemetente;
    private Pair<String,Long> usuarioDestinatario;
    private String timestamp;

    public MensagemDTO(String conteudo, Pair<String, Long> usuarioRemetente, Pair<String, Long> usuarioDestinatario, String timestamp) {
        this.conteudo = conteudo;
        this.usuarioRemetente = usuarioRemetente;
        this.usuarioDestinatario = usuarioDestinatario;
        this.timestamp = timestamp;
    }


    public static MensagemDTO MensagemDTO(String conteudo, Long usuarioRemetente, Long usuarioDestinatario) {
        return new MensagemDTO(conteudo, Pair.of(null, usuarioRemetente), Pair.of(null, usuarioDestinatario), null);
    }

    public MensagemDTO() {}

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
