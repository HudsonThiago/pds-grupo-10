package com.sip.sip.model;

import com.sip.sip.dto.MensagemCEnviadaDTO;
import com.sip.sip.dto.MensagemPDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class MensagemChat extends Mensagem {

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
}
