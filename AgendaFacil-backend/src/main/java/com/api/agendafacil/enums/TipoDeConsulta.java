package com.api.agendafacil.enums;

public enum TipoDeConsulta {
	MEDICO(5),
	ENFERMEIRO(5),
	DENTISTA(5);

	// Criação de um construtor para me dar acesso a quantidade de vagas que tenho disponiveis
	private int vagasDisponiveis;
	TipoDeConsulta(int vagasDisponiveis) {
		// TODO Auto-generated constructor stub
	}
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}
	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	
	
}
