package com.sip.sip.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProjetoCadastroDTO {
        private String nome;
        private String descricao;
        private int horasPorSemana;
        private int diasPorSemana;
        private int numDeVagas;
        private List<Long> tecnologiasEscolhidasId;
        private List<Long> cargosEscolhidosId;
        private MultipartFile imagem;

        public ProjetoCadastroDTO(String nome, String descricao, int horasPorSemana, int diasPorSemana, int numDeVagas, List<Long> tecnologiasEscolhidasId, List<Long> cargosEscolhidosId) {
                this.nome = nome;
                this.descricao = descricao;
                this.horasPorSemana = horasPorSemana;
                this.diasPorSemana = diasPorSemana;
                this.numDeVagas = numDeVagas;
                this.tecnologiasEscolhidasId = tecnologiasEscolhidasId;
                this.cargosEscolhidosId = cargosEscolhidosId;
        }
        public ProjetoCadastroDTO() {};

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

        public List<Long> getTecnologiasEscolhidasId() {
                return tecnologiasEscolhidasId;
        }

        public void setTecnologiasEscolhidasId(List<Long> tecnologiasEscolhidasId) {
                this.tecnologiasEscolhidasId = tecnologiasEscolhidasId;
        }

        public List<Long> getCargosEscolhidosId() {
                return cargosEscolhidosId;
        }

        public void setCargosEscolhidosId(List<Long> cargosEscolhidosId) {
                this.cargosEscolhidosId = cargosEscolhidosId;
        }

        public MultipartFile getImagem() {
                return imagem;
        }

        public void setImagem(MultipartFile imagem) {
                this.imagem = imagem;
        }
}
