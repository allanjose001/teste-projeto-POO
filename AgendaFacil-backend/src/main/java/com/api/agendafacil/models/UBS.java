package com.api.agendafacil.models;

/*classe modelo para o banco de dados
 *é ela que define o formato do banco de dados
 *com ela é possivel adicionar ou remover colunas de informações a
 *serem escritas no banco de dados
*/

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class UBS implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String imagemUBS;
	@Column(nullable = false, length = 200)
	private String nomeUBS;
	@Column(nullable = false)
	private LocalDateTime registrationDate;
	@Column(nullable = false, length = 200)
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;
	
	//getters e setters
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
