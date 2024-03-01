package com.api.agendafacil.dtos;
/*classe de Data Transfer Object
 * classe criada para servir de ponte segura dos dados
 * de um lugar para o outro
 * (para mais informações pesquisar por classe DTO ao Gepeto)
 * */

import com.api.agendafacil.models.Endereco;

public class UBSDto {
	
	private String imagemUBS;
	private String nomeUBS;
	private Endereco endereco;
	
	//getters e setters
	
	public String getImagemUBS() {
		return imagemUBS;
	}
	public void setImagemUBS(String imagemUBS) {
		this.imagemUBS = imagemUBS;
	}
	public String getNomeUBS() {
		return nomeUBS;
	}
	public void setNomeUBS(String nomeUBS) {
		this.nomeUBS = nomeUBS;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
