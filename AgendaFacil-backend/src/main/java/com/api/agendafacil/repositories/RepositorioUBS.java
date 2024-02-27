package com.api.agendafacil.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agendafacil.models.UBS;

@Repository
public interface RepositorioUBS extends JpaRepository<UBS, UUID> {
	
	boolean existsByNomeUBS(String nomeUBS);
}