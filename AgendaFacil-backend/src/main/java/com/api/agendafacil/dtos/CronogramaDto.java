package com.api.agendafacil.dtos;

import java.util.List;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.UBS;

public class CronogramaDto {

	private UBS ubs;
	private List<String> diasSemana;
	private List<TipoDeConsulta> tiposConsulta;
	private List<Integer> vagas;
	private String nomeUBSTransient;
	
	//getters e setters
	

	public UBS getUbs() {
		return ubs;
	}
	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}
	public List<String> getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(List<String> diasSemana) {
		this.diasSemana = diasSemana;
	}
	public List<TipoDeConsulta> getTiposConsulta() {
		return tiposConsulta;
	}
	public void setTiposConsulta(List<TipoDeConsulta> tiposConsulta) {
		this.tiposConsulta = tiposConsulta;
	}
	public List<Integer> getVagas() {
		return vagas;
	}
	public void setVagas(List<Integer> vagas) {
		this.vagas = vagas;
	}
	public String getNomeUBSTransient() {
		return nomeUBSTransient;
	}
	public void setNomeUBSTransient(String nomeUBSTransient) {
		this.nomeUBSTransient = nomeUBSTransient;
	}
}
