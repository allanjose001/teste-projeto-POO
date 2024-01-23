package com.api.agendafacil.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	
	//getters e setters
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
