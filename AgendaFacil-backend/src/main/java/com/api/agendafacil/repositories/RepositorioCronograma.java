package com.api.agendafacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.agendafacil.models.Cronograma;
import java.util.UUID;

@Repository
public interface RepositorioCronograma extends JpaRepository<Cronograma, UUID> {
	List<Cronograma> findByUbs(UBS ubs);
}

