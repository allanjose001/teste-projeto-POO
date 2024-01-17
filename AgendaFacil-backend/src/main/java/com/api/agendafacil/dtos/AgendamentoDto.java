package com.api.agendafacil.dtos;

import jakarta.validation.constraints.NotBlank;

public class AgendamentoDto {
	
	@NotBlank
	private String nome;

	//Getters e Setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
