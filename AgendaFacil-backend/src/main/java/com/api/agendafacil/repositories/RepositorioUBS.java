	package com.api.agendafacil.repositories;

//classe repositorio

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agendafacil.models.AgendaFacilModelo;

@Repository
public interface RepositorioUBS extends JpaRepository<AgendaFacilModelo, UUID> {
	
	boolean existsByNomeUBS(String nomeUBS);
}