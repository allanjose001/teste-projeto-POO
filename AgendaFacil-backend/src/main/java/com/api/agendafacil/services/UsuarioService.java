package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Usuario;
import com.api.agendafacil.repositories.RepositorioUsuario;

import jakarta.transaction.Transactional;

/**
 * classe de servico de Usuario, contem as regras basicas de negocio
 * para que um Usuario possa ser criada
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 */
@Service
public class UsuarioService implements UsuarioServiceInterface{
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Transactional
	public Usuario saveUsuario(Usuario usuario) {
		try {
			// Tenta salvar o usuário no banco de dados
			return repositorioUsuario.save(usuario);
	    } catch (DataIntegrityViolationException ex) {
	        // Captura exceção de violação de integridade (por exemplo, CPF duplicado)
	       
	    	throw new IllegalArgumentException("Erro ao salvar o usuário. Verifique os dados fornecidos.", ex);
	     }
	}
	
	public List<Usuario> getAllUsuario() {
		return repositorioUsuario.findAll();
	}
	 
	public Optional<Usuario> findUsuarioById(UUID id) {
		return repositorioUsuario.findById(id);
	}

	 @Transactional
	 public void deleteUsuario(Usuario usuario) {
		 // Verifica se o usuário existe antes de tentar excluí-lo
		 if (repositorioUsuario.existsById(usuario.getId())) {
			 repositorioUsuario.delete(usuario);
	     } else {
	     // Lança uma exceção se o usuário não existir para exclusão
	    	 throw new IllegalArgumentException("Usuário não encontrado para exclusão.");
	     }
	 }
}