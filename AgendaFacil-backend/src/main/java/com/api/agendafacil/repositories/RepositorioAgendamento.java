package com.api.agendafacil.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agendafacil.models.Agendamento;

@Repository
public interface RepositorioAgendamento extends JpaRepository<Agendamento, UUID> {
	
}
