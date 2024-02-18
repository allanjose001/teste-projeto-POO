package com.api.agendafacil.models;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.api.agendafacil.enums.TipoDeConsulta;

@Entity
public class Cronograma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable=false, length=200)
	private LocalDate data;
	@Column(nullable=false, length=200,columnDefinition = "smallint USING tipo_consulta::smallint")
	private TipoDeConsulta tipoConsulta;
	
	//opcional
	public Cronograma(LocalDate data, TipoDeConsulta tipoConsulta) {
		this.data=data;
		this.tipoConsulta=tipoConsulta;
		
	}
	
	public Cronograma() {
		
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
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public TipoDeConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
	

}
