package com.api.agendafacil.dtos;

import java.time.LocalDate;

public class CronogramaDto {

	private LocalDate data;
	private String tipoConsulta;
	
	//getters e setters
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
}