package com.api.agendafacil.dtos;
import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.Usuario;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AgendamentoDto {
	
	@NotBlank
	private  TipoDeConsulta tipoConsulta;
	@NotNull
	private LocalDate dataConsulta;
	@NotNull
	private Usuario usuario;
	
	//Getters e Setters
	public TipoDeConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public LocalDate getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(LocalDate dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
