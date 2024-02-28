package com.api.agendafacil.dtos;
/**
 * DTO (Data Transfer Object) para representar os detalhes de um endereço.
 * Este objeto é usado para transferir dados entre o controlador e o serviço.
 * 
 *  @author Alcielma
 *  @author Allan
 *  @author Pedro
 */
public class EnderecoDto {

	private String rua; //Representa o nome da rua onde está localizado o endereço.
	private String bairro; //Representa o nome do bairro onde está localizado o endereço.
	private String cidade;// Representa o nome da cidade onde está localizado o endereço.
	private String estado;// Representa o nome do estado onde está localizado o endereço.
	
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
}
