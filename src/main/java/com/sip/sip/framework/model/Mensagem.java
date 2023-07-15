package com.sip.sip.framework.model;

import jakarta.persistence.*;

@Entity
public abstract class Mensagem {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String conteudo;
    @OneToOne
    private Usuario usuarioRemetente;
    private String timestamp;

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

    public Usuario getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(Usuario usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
