package com.api.agendafacil.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.agendafacil.models.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, UUID> {
	Usuario findByNome(String nome);
}
