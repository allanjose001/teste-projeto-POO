package com.api.agendafacil.dtos;

import com.api.agendafacil.models.Endereco;

/**
 * classe de transferencia de dados de UBS, é utilizada 
 * para passar parametros de salvamento no controller da classe.
 * Como cronograma e agendamento só são posteriormente adicionados por relação,
 * não é necessario declarar as listas de agendamento e cronograma
 * 
 * @author Allan
 */
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
