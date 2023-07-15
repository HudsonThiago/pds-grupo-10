package com.sip.sip.dto;

import java.util.List;

public class FiltroDTO {


    private int minCurtidas;
    private int minFavoritos;
    private int horasPorSemana;
    private int diasPorSemana;



    private String procurandoVagas;
    private String emDesenvolvimento;

    private List<Long> habilidadesEscolhidasId;

    private List<Long> cargosEscolhidosId;

    public List<Long> getCargosEscolhidosId() {
        return cargosEscolhidosId;
    }

    public void setCargosEscolhidosId(List<Long> cargosEscolhidosId) {
        this.cargosEscolhidosId = cargosEscolhidosId;
    }

    public List<Long> getHabilidadesEscolhidasId() {
        return habilidadesEscolhidasId;
    }

    public void setHabilidadesEscolhidasId(List<Long> habilidadesEscolhidasId) {
        this.habilidadesEscolhidasId = habilidadesEscolhidasId;
    }

    public int getMinCurtidas() {
        return minCurtidas;
    }

    public void setMinCurtidas(int minCurtidas) {
        this.minCurtidas = minCurtidas;
    }

    public int getMinFavoritos() {
        return minFavoritos;
    }

    public void setMinFavoritos(int minFavoritos) {
        this.minFavoritos = minFavoritos;
    }

    public int getHorasPorSemana() {
        return horasPorSemana;
    }

    public void setHorasPorSemana(int horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }

    public int getDiasPorSemana() {
        return diasPorSemana;
    }

    public void setDiasPorSemana(int diasPorSemana) {
        this.diasPorSemana = diasPorSemana;
    }

    public String getProcurandoVagas() {
        return procurandoVagas;
    }

    public void setProcurandoVagas(String procurandoVagas) {
        this.procurandoVagas = procurandoVagas;
    }

    public String getEmDesenvolvimento() {
        return emDesenvolvimento;
    }

    public void setEmDesenvolvimento(String emDesenvolvimento) {
        this.emDesenvolvimento = emDesenvolvimento;
    }


}