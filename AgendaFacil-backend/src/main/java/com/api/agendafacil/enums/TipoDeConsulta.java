package com.api.agendafacil.enums;
/**
 * Enumeração que representa os tipos de consulta disponíveis no sistema.
 * Cada tipo de consulta tem uma quantidade de vagas disponíveis.
 * 
 * @author Alcielma
 */

public enum TipoDeConsulta {
	
	MEDICO(1), // Tipo de consulta comum para médicos.
	ENFERMEIRO(1), // Tipo de consulta comum para enfermeiro.
	DENTISTA(1), // Tipo de consulta comum para dentista.
	PEDIATRA(1); // Tipo de consulta comum para pediatra.

	private int vagasDisponiveis; // Quantidade de vagas disponíveis para este tipo de consulta
	
	 /**
     * Construtor que associa uma quantidade inicial de vagas disponíveis a cada tipo de consulta.
     * @param vagasDisponiveis a quantidade inicial de vagas disponíveis.
     * 
     */
	TipoDeConsulta(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	
	//Getters e Setters
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}
	
	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	
	
}
