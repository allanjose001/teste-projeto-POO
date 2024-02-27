package com.api.agendafacil.dtos;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.models.Usuario;
import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) para representar os detalhes de um agendamento.
 * Este objeto é usado para transferir dados entre o controlador e o serviço.
 * 
 *  @author Alcielma
 *  @author Allan
 *  @author Pedro
 */
public class AgendamentoDto {
	
	private Usuario usuario;// O usuário associado ao agendamento
	private UBS ubs; // A Unidade Básica de Saúde (UBS) associada ao agendamento
	private LocalDate dataConsulta; // A data da consulta agendada
	private TipoDeConsulta tipoConsulta;// O tipo de consulta agendada
	private String nomeUBSTransient;// Nome da UBS (atributo transitório, não persistido)
	private String nomeUsuarioTransient; // Nome do usuário (atributo transitório, não persistido)
	private String dataConsultaTransient; // Data da consulta formatada (atributo transitório, não persistido)
	
	//Getters e Setters
	
	public String getNomeUBSTransient() {
		return nomeUBSTransient;
	}
	public void setNomeUBSTransient(String nomeUBSTransient) {
		this.nomeUBSTransient = nomeUBSTransient;
	}
	public String getNomeUsuarioTransient() {
		return nomeUsuarioTransient;
	}
	public void setNomeUsuarioTransient(String nomeUsuarioTransient) {
		this.nomeUsuarioTransient = nomeUsuarioTransient;
	}
	public String getDataConsultaTransient() {
		return dataConsultaTransient;
	}
	public void setDataConsultaTransient(String dataConsultaTransient) {
		this.dataConsultaTransient = dataConsultaTransient;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public UBS getUbs() {
		return ubs;
	}
	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}
	public LocalDate getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public TipoDeConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
	
	
}
