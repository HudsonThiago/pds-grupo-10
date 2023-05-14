package com.sip.sip.model;

import jakarta.persistence.*;

@Entity
public class MensagemPrivada extends Mensagem {
    @OneToOne
    private Usuario usuarioDestinatario;

    public Usuario getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Usuario usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }


}
