package com.sip.sip.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
	@OneToOne(cascade=CascadeType.ALL)
	private Usuario criador;
	@JoinColumn
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Tecnologia> tecnologias;
	@Column
	@OneToMany(cascade=CascadeType.ALL)
	private List<Cargo> cargosDesejados;
	@Column
	@OneToMany(cascade=CascadeType.ALL)
	private List<Usuario> membros;
	@PrimaryKeyJoinColumn
	@OneToOne(cascade=CascadeType.ALL)
	private Disponibilidade disponibilidade;
	@Column
	@OneToMany(cascade=CascadeType.ALL)
	private List<Cargo> cargosAbertos;
	@Column
	private int numDeVagas;
	@Column
	private boolean procurandoVagas;
	@Column
	private boolean emDesenvolvimento;
	@Column
	private String imagemUrl = "/imagens/default.jpg";
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

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public int getNumFavoritos() {
		return numFavoritos;
	}

	public void setNumFavoritos(int numFavoritos) {
		this.numFavoritos = numFavoritos;
	}

	public int getNumCurtidas() {
		return numCurtidas;
	}

	public void setNumCurtidas(int numCurtidas) {
		this.numCurtidas = numCurtidas;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagem) {
		this.imagemUrl = imagem;
	}

	public boolean isEmDesenvolvimento() {
		return emDesenvolvimento;
	}

	public void setEmDesenvolvimento(boolean emDesenvolvimento) {
		this.emDesenvolvimento = emDesenvolvimento;
	}

	public boolean isProcurandoVagas() {
		return procurandoVagas;
	}

	public void setProcurandoVagas(boolean procurandoVagas) {
		this.procurandoVagas = procurandoVagas;
	}

	public int getNumDeVagas() {
		return numDeVagas;
	}

	public void setNumDeVagas(int numDeVagas) {
		this.numDeVagas = numDeVagas;
	}

	public List<Cargo> getCargosAbertos() {
		return cargosAbertos;
	}

	public void setCargosAbertos(ArrayList<Cargo> cargosAbertos) {
		this.cargosAbertos = cargosAbertos;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public List<Usuario> getMembros() {
		return membros;
	}

	public void setMembros(ArrayList<Usuario> membros) {
		this.membros = membros;
	}

	public List<Cargo> getCargosDesejados() {
		return cargosDesejados;
	}

	public void setCargosDesejados(ArrayList<Cargo> cargosDesejados) {
		this.cargosDesejados = cargosDesejados;
	}

	public List<Tecnologia> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(ArrayList<Tecnologia> tecnologias) {
		this.tecnologias = tecnologias;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}
}
