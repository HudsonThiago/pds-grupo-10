package com.sip.sip.dto;


public class MensagemEnviadaDTO {
    private String conteudo;
    private Long usuarioDestinatario;

    public MensagemEnviadaDTO(String conteudo, Long usuarioRemetente, Long usuarioDestinatario) {
        this.conteudo = conteudo;
        this.usuarioDestinatario = usuarioDestinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Long getUsuarioDestinatario() {
        return usuarioDestinatario;
    }

    public void setUsuarioDestinatario(Long usuarioDestinatario) {
        this.usuarioDestinatario = usuarioDestinatario;
    }
}
