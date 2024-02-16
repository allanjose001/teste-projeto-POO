package com.api.agendafacil.models;

import java.time.LocalDateTime;
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
	private LocalDateTime dataHora;
	@Column(nullable=false, length=200,columnDefinition = "smallint USING tipo_consulta::smallint")
	private TipoDeConsulta tipoConsulta;
	
	
	public Cronograma(LocalDateTime dataHora, TipoDeConsulta tipoConsulta) {
		this.dataHora=dataHora;
		this.tipoConsulta=tipoConsulta;
		
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public LocalDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public TipoDeConsulta getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	
	

}
