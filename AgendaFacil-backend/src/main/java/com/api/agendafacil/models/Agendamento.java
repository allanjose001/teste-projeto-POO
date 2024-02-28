package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;


@Entity
public class Agendamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne
	@JoinColumn (name="usuario_id", nullable=false)
	@JsonIgnore
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn (name="ubs_id", nullable=false)
	@JsonIgnore
	private UBS ubs;
	
	@Column(nullable=false)
	private LocalDate dataConsulta;

	private TipoDeConsulta tipoConsulta;
	//transients
	
	@Transient
	private String nomeUBSTransient;
	@Transient
	private String nomeUsuarioTransient;
	@Transient
	private String dataConsultaTransient;
	
	//getters Transient
	
	public String getNomeUBSTransient() {
		return ubs != null ? ubs.getNomeUBS() : null;
	}
	
	public String getNomeUsuarioTransient() {
		return usuario != null ? usuario.getNome() : null;
	}
	
	public String getDataConsultaTransient() {
		return dataConsulta != null ? dataConsulta.toString() : null;
	}
	
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
	public UBS getUbs() {
		return ubs;
	}
	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
