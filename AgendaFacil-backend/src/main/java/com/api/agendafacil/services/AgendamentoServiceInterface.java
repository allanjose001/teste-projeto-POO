package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.models.Agendamento;

public interface AgendamentoServiceInterface {
	Agendamento saveAgendamento(Agendamento agendamento);
	Agendamento updateAgendamento(UUID id, AgendamentoDto agendamentoDto);
	Optional<Agendamento> findAgendamentoById(UUID id);
	List<Agendamento> getAllAgendamento();
	void deleteAgendamento(Agendamento agendamento);
}
