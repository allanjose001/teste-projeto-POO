package com.api.agendafacil.services;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import com.api.agendafacil.dtos.AgendamentoDto;

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
	 @Transactional
	    public void updateAgendamento(Agendamento agendamento, AgendamentoDto agendamentoDto) {
	        // Verifica se a data da consulta é válida
	        LocalDate dataAtual = LocalDate.now();
	        LocalDate dataAgendamentoOriginal = agendamentoDto.getDataConsulta();
	        
	        // Verifica se a nova data de consulta é pelo menos 2 dias depois da data atual
	        long diferencaDias = ChronoUnit.DAYS.between(dataAtual, dataAgendamentoOriginal);
	        if (diferencaDias < 1) {
	            throw new IllegalArgumentException("Não é possível alterar o agendamento com menos de 2 dias de antecedência.");
	        }else {
	        // Copia as propriedades do DTO para a entidade Agendamento
	        BeanUtils.copyProperties(agendamentoDto, agendamento);

	        // Salva o agendamento atualizado
	        repositorioAgendamento.save(agendamento);
	        }
	    }    
	}
