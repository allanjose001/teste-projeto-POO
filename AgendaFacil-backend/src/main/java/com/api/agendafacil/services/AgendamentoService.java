package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Agendamento;
import com.api.agendafacil.repositories.RepositorioAgendamento;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoService {

	@Autowired
	private RepositorioAgendamento repositorioAgendamento;
	
	@Transactional
	public Agendamento save(Agendamento agendamento) {
		return repositorioAgendamento.save(agendamento);
	}
	public List<Agendamento> findAll() {
		return repositorioAgendamento.findAll();
	}
	
	public Optional<Agendamento> findById(UUID id) {
		return repositorioAgendamento.findById(id);
	}
	
	@Transactional
	public void delete(Agendamento agendamento) {
		repositorioAgendamento.delete(agendamento);
	}
}
