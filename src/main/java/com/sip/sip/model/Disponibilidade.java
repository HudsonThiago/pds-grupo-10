package com.sip.sip.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Disponibilidade {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private int horasPorSemana;
    private int diasPorSemana;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
