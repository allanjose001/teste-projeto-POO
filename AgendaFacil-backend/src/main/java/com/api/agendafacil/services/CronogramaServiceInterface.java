package com.api.agendafacil.services;

import java.util.List;
import java.util.UUID;

import com.api.agendafacil.models.Cronograma;

public interface CronogramaServiceInterface {
	void adicionarEvento(Cronograma cronograma);
	void atualizarEvento(UUID id, Cronograma novoCronograma);
	List<Cronograma> listarEventos();
}
