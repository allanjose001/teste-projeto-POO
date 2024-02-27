package com.api.agendafacil.models;

public class AuthRequest {
	private String nome;
	private String senha;
	
	public AuthRequest(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public AuthRequest() {
		
	}
	
	//getters e setters
	
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
	
	
}
