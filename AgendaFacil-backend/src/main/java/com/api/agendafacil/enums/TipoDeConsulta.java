package com.api.agendafacil.enums;

public enum TipoDeConsulta {
	MEDICO(1),
	ENFERMEIRO(1),
	DENTISTA(1),
	PEDIATRA(1);

	// Criação de um construtor para me dar acesso a quantidade de vagas que tenho disponiveis
	private int vagasDisponiveis;
	TipoDeConsulta(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}
	
	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	
	
}
