package com.api.agendafacil.dtos;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.models.Usuario;
import java.time.LocalDate;

public class AgendamentoDto {
	
	private Usuario usuario;
	private UBS ubs;
	private LocalDate dataConsulta;
	private TipoDeConsulta tipoConsulta;
	private String nomeUBSTransient;
	private String nomeUsuarioTransient;
	private String dataConsultaTransient;
	
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
