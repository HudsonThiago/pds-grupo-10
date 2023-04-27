package com.sip.sip.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private String descricao;
    @Column
    @OneToMany(cascade=CascadeType.ALL)
    private List<Tecnologia> tecnologias;
    @Column
    @OneToMany(cascade=CascadeType.ALL)
    private List<Projeto> projetosParticipados;
    @Column
    @OneToMany(cascade=CascadeType.ALL)
    private List<Projeto> projetosCurtidos;
    @Column
    @OneToMany(cascade=CascadeType.ALL)
    private List<Projeto> projetosFavoritados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Tecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<Tecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    public List<Projeto> getProjetosParticipados() {
        return projetosParticipados;
    }

    public void setProjetosParticipados(List<Projeto> projetosParticipados) {
        this.projetosParticipados = projetosParticipados;
    }

    public List<Projeto> getProjetosCurtidos() {
        return projetosCurtidos;
    }

    public void setProjetosCurtidos(List<Projeto> projetosCurtidos) {
        this.projetosCurtidos = projetosCurtidos;
    }

    public List<Projeto> getProjetosFavoritados() {
        return projetosFavoritados;
    }

    public void setProjetosFavoritados(List<Projeto> projetosFavoritados) {
        this.projetosFavoritados = projetosFavoritados;
    }

    /*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    */
}
