package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.api.agendafacil.enums.TipoUsuario;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String nome;
	@Column(nullable = false, length = 200)
	private LocalDate nascimento;
	@Column(nullable = false, unique = true, length = 200)
	private String cpf;
	@Column(nullable = false, unique = true, length = 200)
	private String telefone;
	@Column(nullable = false, length = 200)
	private String email;
	@Embedded
	private Endereco endereco;
	
	@ElementCollection(targetClass = TipoUsuario.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_roles")
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<TipoUsuario> roles;

	public Usuario(UUID id, String nome, LocalDate nascimento, String cpf, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.addRole(TipoUsuario.USUARIO);
	}
	
	public Usuario() {
		this.addRole(TipoUsuario.USUARIO);
		
	}
	
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

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
