package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cronograma implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@OneToOne
	@JoinColumn (name="ubs_id", nullable=false)
	@JsonIgnore
	private UBS ubs;
	

	@Column(nullable=false, length=200)
	private LocalDate data;
	@Column(nullable=false, length=200)
	private TipoDeConsulta tipoConsulta;
	
	@Transient
	private String nomeUBSTransient;
	
	public Cronograma(LocalDate data, TipoDeConsulta tipoConsulta) {
		this.data=data;
		this.tipoConsulta=tipoConsulta;
		
	}
	
	public Cronograma() {
		
	}
	
	//getter transient
	public String getNomeUBSTransient() {
		return ubs != null ? ubs.getNomeUBS() : null;
	}
	
	//getter e setters
	
	public UBS getUbs() {
		return ubs;
	}
	
	public void setUbs(UBS ubs) {
		this.ubs = ubs;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocalDate getData() {
		return data;
	}
	public void setDataHora(LocalDate data) {
		this.data = data;
	}
	public TipoDeConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	
}