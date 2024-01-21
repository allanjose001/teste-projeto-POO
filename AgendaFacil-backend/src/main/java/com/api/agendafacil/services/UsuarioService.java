package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
		return repositorioUsuario.save(usuario);
	}
	
	public List<Usuario> findAll() {
		return repositorioUsuario.findAll();
	}

	public Optional<Usuario> findById(UUID id) {
		return repositorioUsuario.findById(id);
	}

	@Transactional
	public void delete(Usuario usuario) {
		repositorioUsuario.delete(usuario);
	}
}