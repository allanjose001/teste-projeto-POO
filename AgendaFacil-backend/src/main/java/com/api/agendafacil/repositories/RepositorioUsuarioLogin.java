package com.api.agendafacil.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.agendafacil.models.UsuarioLogin;

public interface RepositorioUsuarioLogin extends JpaRepository<UsuarioLogin, UUID>  {
	UsuarioLogin findByNome(String nome);
}
