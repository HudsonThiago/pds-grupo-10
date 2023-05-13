package com.sip.sip.dto;


public class MensagemEnviadaDTO {
    private String conteudo;
    private Long usuarioDestinatario;
    private Long usuarioRemetente;

    public MensagemEnviadaDTO(String conteudo, Long usuarioRemetente, Long usuarioDestinatario) {
        this.conteudo = conteudo;
        this.usuarioDestinatario = usuarioDestinatario;
        this.usuarioRemetente = usuarioRemetente;
    }

    public Long getUsuarioRemetente() {
        return usuarioRemetente;
    }

    public void setUsuarioRemetente(Long usuarioRemetente) {
        this.usuarioRemetente = usuarioRemetente;
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
