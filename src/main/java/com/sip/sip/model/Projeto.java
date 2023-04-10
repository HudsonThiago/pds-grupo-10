package com.sip.sip.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Projeto {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String nome;
	@Column
	private String descricao;
	@PrimaryKeyJoinColumn
	@OneToOne
	private Usuario criador;
	@Column
	@OneToMany
	private ArrayList<Tecnologia> tecnologias;
	@Column
	@OneToMany
	private ArrayList<Cargo> cargosDesejados;
	@Column
	@OneToMany
	private ArrayList<Usuario> membros;
	@PrimaryKeyJoinColumn
	@OneToOne
	private Disponibilidade disponibilidade;
	@Column
	@OneToMany
	private ArrayList<Cargo> cargosAbertos;
	@Column
	private int numDeVagas;
	@Column
	private boolean procurandoVagas;
	@Column
	private boolean emDesenvolvimento;
	@Column
	private String imagem;
	@Column
	private String dataCriacao;
	@Column
	private int numCurtidas;
	@Column
	private int numFavoritos;
	
	public Projeto() {
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
