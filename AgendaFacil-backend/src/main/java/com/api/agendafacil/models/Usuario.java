package com.api.agendafacil.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.api.agendafacil.enums.TipoUsuario;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, length = 200)
	private String nome;
	@Column(nullable = false, length = 200)
	private LocalDate dataDeNascimento;
	@Column(nullable = false, unique = true, length = 200)
	private String cpf;
	@Column(nullable=false,length=200)
	private String sus;
	@Column(nullable = false, unique = true, length = 200)
	private String telefone;
	@Column(nullable = false, length = 200)
	private String email;
	
	@ElementCollection(targetClass = TipoUsuario.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_roles")
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<TipoUsuario> roles;

	public Usuario(UUID id, String nome, LocalDate dataDeNascimento, String cpf, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.addRole(TipoUsuario.USUARIO);
	}
	
	public Usuario() {
		this.addRole(TipoUsuario.USUARIO);
		
	}
	
	public void addRole(TipoUsuario role) {
		if (this.roles == null) {
			roles = new HashSet<>();	
		}
		roles.add(role);
	
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id; 
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome==null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("nome não pode ser nulo ou vazio");
		}
		if(nome.length()<2||nome.length()>100) {
			throw new IllegalArgumentException("o comprimento do nome deve estar entre 2 e 100 caracteres");
		}
		 if (!nome.matches("^[a-zA-ZÀ-ÖØ-öø-ÿ\\s\\-]*$")) {
	            throw new IllegalArgumentException("Nome contém caracteres inválidos");
	    }
		this.nome = nome;
	}

	public LocalDate getNascimento() {
		return dataDeNascimento;
	}

	public void setNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if(isValidCPF(cpf)) {
			this.cpf=cpf;
		}else {
				throw new IllegalArgumentException("CPF invalido");
			}
		}
	private boolean isValidCPF(String cpf) {
		// Remove caracteres não numericos
		cpf=cpf.replaceAll("[^0-9]","");
		if(cpf.length() > 11) {
			return false;
		}
		//verificação se todos os digitos são iguais 
		if(cpf.matches("(\\d)\\1{10}")) {
			return false;
		}
		//calcular o primeiro digito verificador
		int soma=0;
		for(int i =0; i<9;i++) {
			soma+=Character.getNumericValue(cpf.charAt(i)*(10-i));
		}
		int primeiroDigito=11-(soma%11);
		if(primeiroDigito>9) {
			primeiroDigito=0;
		}
		 // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }
		//verificar se os digitos calculados correspondem aos digitos informados
		return Character.getNumericValue(cpf.charAt(9))==primeiroDigito 
				&& Character.getNumericValue(cpf.charAt(10))==segundoDigito;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
        }
		// Padrão de expressão regular para validar números de telefone com ou sem código de área
        String regex = "^(\\+\\d{1,3})?\\s*\\(?\\d{2,4}\\)?[-.\\s]?\\d{6,15}$";

        if (!telefone.matches(regex) || telefone.length() > 15) {
            throw new IllegalArgumentException("Telefone inválido");
        }
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email== null || email.trim().isEmpty()) {
			throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio");
		}
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";
	    if (!email.matches(regex)) {
	            throw new IllegalArgumentException("E-mail inválido");
	    }
	    this.email = email;
	}

	public Set<TipoUsuario> getRoles() {
		return roles;
	}

	public void setRoles(Set<TipoUsuario> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataDeNascimento, email, id, nome, roles, sus, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataDeNascimento, other.dataDeNascimento)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(roles, other.roles)
				&& Objects.equals(sus, other.sus) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + ", cpf=" + cpf
				+ ", sus=" + sus + ", telefone=" + telefone + ", email=" + email + ", roles=" + roles + "]";
	}
	
}
