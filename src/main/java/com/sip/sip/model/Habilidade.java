package com.sip.sip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Habilidade {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String nome;
    private Boolean ativo;
    private Long idUsuarioQueSolicitou;
    private Long idAdministradorQueAceitou;

    public Habilidade() {
        this.ativo = true;
    }
    public Habilidade(Long id, String nome, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getIdUsuarioQueSolicitou() {
        return idUsuarioQueSolicitou;
    }

    public void setIdUsuarioQueSolicitou(Long idUsuarioQueSolicitou) {
        this.idUsuarioQueSolicitou = idUsuarioQueSolicitou;
    }

    public Long getIdAdministradorQueAceitou() {
        return idAdministradorQueAceitou;
    }

    public void setIdAdministradorQueAceitou(Long idAdministradorQueAceitou) {
        this.idAdministradorQueAceitou = idAdministradorQueAceitou;
    }
}
