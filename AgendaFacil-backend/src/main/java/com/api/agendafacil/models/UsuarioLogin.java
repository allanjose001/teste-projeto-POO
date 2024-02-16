package com.api.agendafacil.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class UsuarioLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, unique = true)
    private String nome;
    
    @Column(nullable = false)
    private String senha;
    
    //um usuario tem um login
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    public UsuarioLogin() {
    	
    }
    
    public UsuarioLogin(String nome, String senha, Usuario usuario) {
    	this.nome=nome;
    	this.senha=senha;
    	this.usuario=usuario;
    }
    
    //getters e setters

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
