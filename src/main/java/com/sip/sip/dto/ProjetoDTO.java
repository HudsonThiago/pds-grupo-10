package com.sip.sip.dto;

import java.util.List;

public class ProjetoDTO {
        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public String getUsuarioCriadorNome() {
                return usuarioCriadorNome;
        }

        public void setUsuarioCriadorNome(String usuarioCriadorNome) {
                this.usuarioCriadorNome = usuarioCriadorNome;
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

        public int getNumDeVagas() {
                return numDeVagas;
        }

        public void setNumDeVagas(int numDeVagas) {
                this.numDeVagas = numDeVagas;
        }

        private String nome;
        private String descricao;
        private String usuarioCriadorNome;
        private int horasPorSemana;
        private int diasPorSemana;
        private int numDeVagas;



        private List<Long> tecnologiasEscolhidasId;

        public List<Long> getTecnologiasEscolhidasId() {
                return tecnologiasEscolhidasId;
        }

        public void setTecnologiasEscolhidasId(List<Long> tecnologiasEscolhidasId) {
                this.tecnologiasEscolhidasId = tecnologiasEscolhidasId;
        }
}
