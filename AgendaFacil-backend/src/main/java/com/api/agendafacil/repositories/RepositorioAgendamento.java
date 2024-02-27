package com.api.agendafacil.repositories;

/**
 * Repositório para manipulação de objetos Agendamento no banco de dados.
 * Esta interface estende JpaRepository para herdar métodos de manipulação de entidades JPA.
 */

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.agendafacil.models.Agendamento;
@Repository
public interface RepositorioAgendamento extends JpaRepository<Agendamento, UUID> {
	
}
