package com.sip.sip.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
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
	@ManyToMany
	private List<Habilidade> habilidades;
	@Column
	@ManyToMany
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
	@ManyToOne
	private Cidade cidade;
	@OneToMany(mappedBy = "projeto")
	private List<UsuarioProjeto> usuariosProjeto;
	@ManyToMany(mappedBy = "projetosCurtidos")
	private List<Usuario> curtidoPorUsuarios;

	@ManyToMany(mappedBy = "projetosFavoritados")
	private List<Usuario> favoritadoPorUsuarios;

	@Column
	private Boolean emDestaque = false;

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

	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public void setCargosDesejados(List<Cargo> cargosDesejados) {
		this.cargosDesejados = cargosDesejados;
	}

	public void setMembros(List<Usuario> membros) {
		this.membros = membros;
	}

	public void setCargosAbertos(List<Cargo> cargosAbertos) {
		this.cargosAbertos = cargosAbertos;
	}

	public List<UsuarioProjeto> getUsuariosProjeto() {
		return usuariosProjeto;
	}

	public void setUsuariosProjeto(List<UsuarioProjeto> usuariosProjeto) {
		this.usuariosProjeto = usuariosProjeto;
	}

	public List<Usuario> getCurtidoPorUsuarios() {
		return curtidoPorUsuarios;
	}

	public void setCurtidoPorUsuarios(List<Usuario> curtidoPorUsuarios) {
		this.curtidoPorUsuarios = curtidoPorUsuarios;
	}

	public List<Usuario> getFavoritadoPorUsuarios() {
		return favoritadoPorUsuarios;
	}

	public void setFavoritadoPorUsuarios(List<Usuario> favoritadoPorUsuarios) {
		this.favoritadoPorUsuarios = favoritadoPorUsuarios;
	}

	public Boolean getEmDestaque() {
		return emDestaque;
	}

	public void setEmDestaque(Boolean emDestaque) {
		this.emDestaque = emDestaque;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Cidade getCidade() {
		return this.cidade;
	}
}
