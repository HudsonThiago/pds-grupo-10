package com.sip.sip.dto;

import com.sip.sip.model.Tecnologia;

import java.util.List;

public class AtualizarUsuarioDTO {
    private String nome;
    private String email;
    private String descricao;
    private List<Long> idTecnologias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Long> getIdTecnologias() {
        return idTecnologias;
    }

    public void setIdTecnologias(List<Long> idTecnologias) {
        this.idTecnologias = idTecnologias;
    }
}
