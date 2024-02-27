package com.api.agendafacil.services;
import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.enums.TipoDeConsulta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Agendamento;
import com.api.agendafacil.repositories.RepositorioAgendamento;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoService implements AgendamentoServiceInterface {

	@Autowired
	private RepositorioAgendamento repositorioAgendamento;

	@Transactional
    public Agendamento saveAgendamento(Agendamento agendamento) {  
		return repositorioAgendamento.save(agendamento);
    }

    // Outros métodos do serviço
	public List<Agendamento> getAllAgendamento() {
		return repositorioAgendamento.findAll();
	}
	
	public Optional<Agendamento> findAgendamentoById(UUID id) {
		return repositorioAgendamento.findById(id);
	}
	public Agendamento updateAgendamento(UUID id, AgendamentoDto agendamentoDto) {
		Optional<Agendamento> agendamentoOptional = findAgendamentoById(id);
	    if (agendamentoOptional.isPresent()) {
	    	Agendamento agendamento = agendamentoOptional.get();
	    	agendamento.setTipoConsulta(agendamentoDto.getTipoConsulta());
	    	agendamento.setDataConsulta(agendamentoDto.getDataConsulta());
	    	return repositorioAgendamento.save(agendamento);
	    	} else {
	    		return null;
	    }
	}
	
	//implementei a mesma logica do save a diferença é que eu adicionei mais uma vaga
	@Transactional
	public void deleteAgendamento(Agendamento agendamento) {
		TipoDeConsulta tipoConsulta=agendamento.getTipoConsulta();
		if(tipoConsulta!=null && tipoConsulta.getVagasDisponiveis()>0) {
			repositorioAgendamento.delete(agendamento);
			tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis()+1);
		}
	}
	
}