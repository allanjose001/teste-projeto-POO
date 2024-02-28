package com.api.agendafacil.models;

import java.io.Serializable;
import java.util.UUID;

import com.api.agendafacil.dtos.EnderecoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Classe que representa um endereço que será associado a UBS.
 * Um endereço é composto por vários elementos, como rua, bairro, cidade e estado.
 * Esta classe encapsula esses elementos para fornecer uma representação coesa de um endereço.
 * Pode ser utilizado em diferentes contextos, como cadastros de usuários, empresas, entre outros.
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false)
	private String rua;
	@Column(nullable = false)
	private String bairro;
	@Column(nullable = false)
	private String cidade;
	@Column(nullable = false)
	private String estado;
	
	//construtores
	/**
	 * Construtor para criar um objeto Endereco a partir dos componentes individuais.
	 * @param rua O nome da rua do endereço.
	 * @param bairro O nome do bairro do endereço.
	 * @param cidade O nome da cidade do endereço.
	 * @param estado O nome do estado do endereço.
	 */
	public Endereco(String rua, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	/**
	 * Construtor para criar um objeto Endereco a partir de um objeto EnderecoDto.
	 * @param dto O objeto EnderecoDto contendo os dados do endereço.
	 */
	public Endereco(EnderecoDto dto) {
		this.rua = dto.getRua();
		this.bairro = dto.getBairro();
		this.cidade = dto.getCidade();
		this.estado = dto.getEstado();
	}
	
	public Endereco() {
		
	}
	//getters e setters
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
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
