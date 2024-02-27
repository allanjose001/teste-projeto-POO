package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.api.agendafacil.enums.TipoUsuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * classe modelo de Usuario que é criado com nome, data de nascimento
 * cpf, sus, telefonem email e senha.
 * e futuramente pode receber varios agendamentos
 * 
 * @author Alcielma
 * @author Allan
 */
@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "usuario_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String nome;
	@Column(nullable = false, length = 200)
	private LocalDate dataDeNascimento;
	@Column(nullable = false, unique = true, length = 200)
	private String cpf;
	@Column(nullable=false,length=200)
	private String sus;
	@Column(nullable = false, unique = true, length = 200)
	private String telefone;
	@Column(nullable = false, length = 200)
	private String email;
	private String senha;
	//guarda uma lista com os agendamentos feitos por este usuario
	//serve para visualizar as consultas já feitas ou que estão para acontecer
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Agendamento> agendamento;
	
	//recebe um Enum que pode ser Usuario ou Admin, esse enum será inserido numa 
	//collection chamada "usuario_roles", e então o transforma em uma string
	//por fim adiciona ela a coluna de "role"
	@ElementCollection(targetClass = TipoUsuario.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_roles")
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<TipoUsuario> roles;
	
	//construtores

	public Usuario(UUID id, String nome, LocalDate dataDeNascimento, String cpf, String telefone, String email, String senha) {
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.addRole(TipoUsuario.USUARIO);
	}
	
	public Usuario(String nome, LocalDate dataDeNascimento, String cpf, String sus, String telefone, String email,
			String senha) {
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.sus = sus;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.addRole(TipoUsuario.USUARIO);
	}


	public Usuario() {
		this.addRole(TipoUsuario.USUARIO);
	}
	
	//getters e setters
	
	public void addRole(TipoUsuario role) {
		if (this.roles == null) {
			roles = new HashSet<>();	
		}
		roles.add(role);
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSus() {
		return sus;
	}
	public void setSus(String sus) {
		this.sus = sus;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public List<Agendamento> getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(List<Agendamento> agendamento) {
		this.agendamento = agendamento;
	}

	public Set<TipoUsuario> getRoles() {
		return roles;
	}
	public void setRoles(Set<TipoUsuario> roles) {
		this.roles = roles;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
