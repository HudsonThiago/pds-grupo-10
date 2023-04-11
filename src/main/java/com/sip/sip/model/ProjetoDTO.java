package com.sip.sip.model;

import jakarta.persistence.*;

import java.util.List;

public class ProjetoDTO {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private String nome;

    public String getUsuarioCriadorNome() {
        return usuarioCriadorNome;
    }

    public void setUsuarioCriadorNome(String usuarioCriadorNome) {
        this.usuarioCriadorNome = usuarioCriadorNome;
    }

    private String descricao;
    private String usuarioCriadorNome;

    public int getDisponibilidadeHorasPorSemana() {
        return disponibilidadeHorasPorSemana;
    }

    public void setDisponibilidadeHorasPorSemana(int disponibilidadeHorasPorSemana) {
        this.disponibilidadeHorasPorSemana = disponibilidadeHorasPorSemana;
    }

    private int disponibilidadeHorasPorSemana;
    private int disponibilidadeDiasPorSemana;

    public int getDisponibilidadeDiasPorSemana() {
        return disponibilidadeDiasPorSemana;
    }

    public void setDisponibilidadeDiasPorSemana(int disponibilidadeDiasPorSemana) {
        this.disponibilidadeDiasPorSemana = disponibilidadeDiasPorSemana;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumDeVagas() {
        return numDeVagas;
    }

    public void setNumDeVagas(int numDeVagas) {
        this.numDeVagas = numDeVagas;
    }

    private int numDeVagas;
}
