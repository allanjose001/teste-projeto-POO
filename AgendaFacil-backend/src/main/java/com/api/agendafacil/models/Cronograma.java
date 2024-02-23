package com.api.agendafacil.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import com.api.agendafacil.enums.TipoDeConsulta;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cronograma implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "cronograma_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne
	@JoinColumn (name="ubs_id", nullable=false)
	@JsonIgnore
	private UBS ubs;
	
	@ElementCollection
	@CollectionTable(name = "cronograma_dias_semana", joinColumns = @JoinColumn(name = "cronograma_id"))
	@Column(name = "dia_semana")
	private List<String> diasSemana;
	
	@ElementCollection
	@CollectionTable(name = "cronograma_tipos_consulta", joinColumns = @JoinColumn(name = "cronograma_id"))
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_consulta")
	private List<TipoDeConsulta> tiposConsulta;
	
	@ElementCollection
	@CollectionTable(name = "cronograma_vagas", joinColumns = @JoinColumn(name = "cronograma_id"))
	@Column(name = "vagas")
	private List<Integer> vagas;
	
	//transients
	
	@Transient
	private String nomeUBSTransient;
	
	public Cronograma(UUID id, UBS ubs, List<String> diasSemana, List<TipoDeConsulta> tiposConsulta,
			List<Integer> vagas, String nomeUBSTransient) {
		this.id = id;
		this.ubs = ubs;
		this.diasSemana = diasSemana;
		this.tiposConsulta = tiposConsulta;
		this.vagas = vagas;
		this.nomeUBSTransient = nomeUBSTransient;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setNomeUBSTransient(String nomeUBSTransient) {
		this.nomeUBSTransient = nomeUBSTransient;
	}
	
}