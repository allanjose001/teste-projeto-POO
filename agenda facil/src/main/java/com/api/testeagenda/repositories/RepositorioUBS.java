package com.api.testeagenda.repositories;

//classe repositorio

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.testeagenda.models.AgendaFacilModelo;

@Repository
public interface RepositorioUBS extends JpaRepository<AgendaFacilModelo, UUID> {
	
	boolean existsByCodigoUBS(String codigoUBS);
	boolean existsByNomeUBS(String nomeUBS);
}
