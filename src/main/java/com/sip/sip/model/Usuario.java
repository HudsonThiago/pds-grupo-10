package com.sip.sip.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails {
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
    private Boolean administrador;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(){};
    public Usuario(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public Usuario(Long id, String nome, String email, String senha, Boolean administrador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.administrador = administrador;
    }

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

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return getSenha();
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
