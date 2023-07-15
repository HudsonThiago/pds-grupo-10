package com.sip.sip.framework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    public Disponibilidade(int horasPorSemana, int diasPorSemana) {
        this.horasPorSemana = horasPorSemana;
        this.diasPorSemana = diasPorSemana;
    }
    public Disponibilidade() {
    }

    public int getHorasPorSemana() {
        return horasPorSemana;
    }

    public void setHorasPorSemana(int horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }

    private int horasPorSemana;
    private int diasPorSemana;

    public int getDiasPorSemana() {
        return diasPorSemana;
    }

    public void setDiasPorSemana(int diasPorSemana) {
        this.diasPorSemana = diasPorSemana;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
