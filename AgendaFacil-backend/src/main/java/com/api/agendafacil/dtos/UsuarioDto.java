package com.api.agendafacil.dtos;

import java.time.LocalDate;
import java.util.Set;

import com.api.agendafacil.enums.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * classe de transferencia de dados de Usuario, é utilizada 
 * para passar parametros de salvamento no controller da classe.
 * Como agendamento só é adicionado posteriormente por relação,
 * não é necessario declarar a lista de agendamento
 * 
 * @author Alcielma
 * @author Allan
 */
public class UsuarioDto {
	
	@NotBlank
	private String nome;
	@NotNull
	private LocalDate dataDeNascimento;
	@NotBlank
	private String cpf;
	@NotBlank
	private String sus;
	private String telefone;
	private String email;
	private String senha;
	private Set<TipoUsuario> roles;
	
	
	public String getSus() {
		return sus;
	}
	public void setSus(String sus) {
		this.sus = sus;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Set<TipoUsuario> getRoles() {
		return roles;
	}
	public void setRoles(Set<TipoUsuario> roles) {
		this.roles = roles;
	}
}
