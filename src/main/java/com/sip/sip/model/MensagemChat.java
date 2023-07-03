package com.sip.sip.model;

import com.sip.sip.dto.MensagemCEnviadaDTO;
import com.sip.sip.dto.MensagemPDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class MensagemChat extends Mensagem {

    private String arquivoURI = null;
    private String arquivoNome = null;
    MensagemChat(Projeto projeto) {
        this.projetoDestinatario = projeto;
    }

    public MensagemChat() {
    }

    @OneToOne
    private Projeto projetoDestinatario;

    public Projeto getProjetoDestinatario() {
        return projetoDestinatario;
    }

    public void setProjetoDestinatario(Projeto projetoDestinatario) {
        this.projetoDestinatario = projetoDestinatario;
    }

    public String getArquivoURI() {
        return this.arquivoURI;
    }

    public void setArquivoURI(String arquivoURI) {
        this.arquivoURI = arquivoURI;
    }

    public String getArquivoNome() {
        return this.arquivoNome;
    }

    public void setArquivoNome(String arquivoNome) {
        this.arquivoNome = arquivoNome;
    }
}
