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

@Service
public class UsuarioService {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;

	@Transactional
	public Usuario save(Usuario usuario) {
		try {
			// Tenta salvar o usuário no banco de dados
			return repositorioUsuario.save(usuario);
	    } catch (DataIntegrityViolationException ex) {
	        // Captura exceção de violação de integridade (por exemplo, CPF duplicado)
	        // Lança uma exceção personalizada informando sobre o erro de integridade
	    	throw new IllegalArgumentException("Erro ao salvar o usuário. Verifique os dados fornecidos.", ex);
	     }
	}
	//esse metodo retorna uma lista com todos os ussuarios que estão contidos no meu banco de dados
	public List<Usuario> findAll() {
		return repositorioUsuario.findAll();
	}
	
	//metodo de busca usuario por id 
	public Optional<Usuario> findById(UUID id) {
		return repositorioUsuario.findById(id);
	}

	 @Transactional
	 public void delete(Usuario usuario) {
		 // Verifica se o usuário existe antes de tentar excluí-lo
		 if (repositorioUsuario.existsById(usuario.getId())) {
	     // Exclui o usuário se existir
			 repositorioUsuario.delete(usuario);
	     } else {
	     // Lança uma exceção se o usuário não existir para exclusão
	    	 throw new IllegalArgumentException("Usuário não encontrado para exclusão.");
	     }
	 }
}