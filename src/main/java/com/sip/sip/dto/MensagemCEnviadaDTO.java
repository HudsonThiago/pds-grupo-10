package com.sip.sip.dto;


public class MensagemCEnviadaDTO {
    private String conteudo;
    private Long projetoDestinatario;
    // temp todo auth
    private Long usuarioRemetente;

    public MensagemCEnviadaDTO(String conteudo, Long usuarioRemetente, Long projetoDestinatario) {
        this.conteudo = conteudo;
        this.projetoDestinatario = projetoDestinatario;
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

    public Long getProjetoDestinatario() {
        return projetoDestinatario;
    }

    public void setProjetoDestinatario(Long projetoDestinatario) {
        this.projetoDestinatario = projetoDestinatario;
    }
}
