package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.Usuario;

public interface UsuarioServiceInterface {

	public Usuario saveUsuario(Usuario usuario);
	public Optional<Usuario> findUsuarioById(UUID id);
	public List<Usuario> getAllUsuario();
	public void deleteUsuario(Usuario usuario);


}
