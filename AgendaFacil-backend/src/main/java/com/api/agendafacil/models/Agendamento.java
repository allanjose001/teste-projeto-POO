package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.api.agendafacil.enums.TipoDeConsulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne
	@JoinColumn (name="usuario_id", nullable=false)
	private Usuario usuario;
	
	@Column(nullable=false)
	private LocalDate dataConsulta;
	
	@Column(nullable=false, length=200,columnDefinition = "smallint USING tipo_consulta::smallint")
	private TipoDeConsulta tipoConsulta;
	
	//getters e setters
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	public static long getSerialversiouid() {
		return serialVersionUID;
	}
	

	
	
	
}
