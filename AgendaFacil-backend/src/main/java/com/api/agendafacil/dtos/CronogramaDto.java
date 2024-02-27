package com.api.agendafacil.dtos;

import java.util.List;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.UBS;
/**
 * DTO (Data Transfer Object) que representa o cronograma de consultas.
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 */
public class CronogramaDto {

	private UBS ubs;// A unidade básica de saúde associada ao cronograma
	private List<String> diasSemana;// Os dias da semana em que as consultas são agendadas
	private List<TipoDeConsulta> tiposConsulta;// Os tipos de consulta disponíveis
	private List<Integer> vagas; // A quantidade de vagas disponíveis para cada tipo de consulta
	private String nomeUBSTransient; // Nome da UBS, não persistente

	
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