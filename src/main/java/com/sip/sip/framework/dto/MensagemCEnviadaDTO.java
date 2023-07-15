package com.sip.sip.framework.dto;


import org.springframework.web.multipart.MultipartFile;

public class MensagemCEnviadaDTO {
    private String conteudo;
    private Long projetoDestinatario;
    // temp todo auth
    private Long usuarioRemetente;

    private MultipartFile file = null;
    public MensagemCEnviadaDTO(String conteudo, Long usuarioRemetente, Long projetoDestinatario, MultipartFile file) {
        this.conteudo = conteudo;
        this.projetoDestinatario = projetoDestinatario;
        this.usuarioRemetente = usuarioRemetente;
        this.file = file;
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

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }
}
