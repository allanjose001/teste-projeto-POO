package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.Agendamento;

public interface AgendamentoServiceInterface {
	Agendamento saveAgendamento(Agendamento agendamento);
	List<Agendamento> getAllAgendamento();
	Optional<Agendamento> findAgendamentoById(UUID id);
	void deleteAgendamento(Agendamento agendamento);

}
