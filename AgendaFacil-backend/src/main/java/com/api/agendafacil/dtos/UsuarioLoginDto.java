package com.api.agendafacil.dtos;

import jakarta.validation.constraints.NotNull;

public class UsuarioLoginDto {
@NotNull
private String nome;
@NotNull
private String senha;
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
