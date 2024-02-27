package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.Cronograma;

public interface CronogramaServiceInterface {
	Cronograma adicionarCronograma(Cronograma cronograma);
	Cronograma atualizarCronograma(UUID id, Cronograma novoCronograma);
	Optional<Cronograma> listarCronogramaPorId(UUID id);
	List<Cronograma> listarCronograma();
	void deleteCronograma(Cronograma cronograma);
}