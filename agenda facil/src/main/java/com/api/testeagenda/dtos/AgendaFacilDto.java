package com.api.testeagenda.dtos;
/*classe de Data Transfer Object
 * classe criada para servir de ponte segura dos dados
 * de um lugar para o outro
 * (para mais informações pesquisar por classe DTO ao Gepeto)
 * */

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AgendaFacilDto {
	
	@NotBlank
	private String codigoUBS;
	@NotBlank
	@Size(max = 7)
	private String nomeUBS;
	@NotBlank
	private String bairro;
	@NotBlank
	private String rua;
	@NotBlank
	private String nomeResponsavel;
	
	public String getCodigoUBS() {
		return codigoUBS;
	}
	public void setCodigoUBS(String codigoUBS) {
		this.codigoUBS = codigoUBS;
	}
	public String getNomeUBS() {
		return nomeUBS;
	}
	public void setNomeUBS(String nomeUBS) {
		this.nomeUBS = nomeUBS;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNomeResponsavel() {
		return nomeResponsavel;
	}
	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
}
