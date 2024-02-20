package com.api.agendafacil.dtos;

import java.time.LocalDate;

import com.api.agendafacil.models.UBS;

public class CronogramaDto {

	private UBS ubs;
	private LocalDate data;
	private String tipoConsulta;	
	private String nomeUBSTransient;
	
	
	//getters e setters
	
	public LocalDate getData() {
		return data;
	}
	public UBS getUbs() {
		return ubs;
	}
	public void setUbs(UBS ubs) {
		this.ubs = ubs;
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
	public String getNomeUBSTransient() {
		return nomeUBSTransient;
	}
	public void setNomeUBSTransient(String nomeUBSTransient) {
		this.nomeUBSTransient = nomeUBSTransient;
	}
}