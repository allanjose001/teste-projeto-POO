package com.api.agendafacil.dtos;

import com.api.agendafacil.models.Endereco;

public class ProfissionalSaudeDto {

	private String nome;
	private String especializacao;
	private Endereco endereco;
	
	//getters e setters
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEspecializacao() {
		return especializacao;
	}
	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}
