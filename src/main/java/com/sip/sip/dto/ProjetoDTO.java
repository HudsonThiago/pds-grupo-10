package com.sip.sip.dto;


import java.util.List;
import java.util.Map;

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

        private Long id;
        private String nome;
        private String descricao;
        private String usuarioCriadorNome;
        private int horasPorSemana;
        private int diasPorSemana;
        private int numDeVagas;
        private String imagemUrl;

        private String cidadeNome;

        private Boolean emDestaque;

        public String getImagemUrl() {
                return imagemUrl;
        }

        public void setImagemUrl(String imagemUrl) {
                this.imagemUrl = imagemUrl;
        }

        private Map<String, Long> habilidadesEscolhidasId;

        public Map<String, Long> getHabilidadesEscolhidasId() {
                return habilidadesEscolhidasId;
        }

        public void setHabilidadesEscolhidasId(Map<String, Long> habilidadesEscolhidasId) {
                this.habilidadesEscolhidasId = habilidadesEscolhidasId;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        private Map<String,Long> membros;

        private Map<String, List<String>> nomeCargoMap;

        public Map<String, Long> getMembros() {
                return membros;
        }

        public void setMembros(Map<String, Long> membros) {
                this.membros = membros;
        }

        public Map<String, List<String>> getNomeCargoMap() {
                return nomeCargoMap;
        }

        public void setNomeCargoMap(Map<String, List<String>> nomeCargoMap) {
                this.nomeCargoMap = nomeCargoMap;
        }

        public Boolean getEmDestaque() {
                return emDestaque;
        }

        public void setEmDestaque(Boolean emDestaque) {
                this.emDestaque = emDestaque;
        }

        public String getCidadeNome() {
                return cidadeNome;
        }

        public void setCidadeNome(String cidadeNome) {
                this.cidadeNome = cidadeNome;
        }
}
