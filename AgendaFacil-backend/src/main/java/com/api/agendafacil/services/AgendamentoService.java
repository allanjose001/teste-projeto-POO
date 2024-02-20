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

	/*	@Transactional
    public Agendamento saveAgendamento(Agendamento agendamento) {
        TipoDeConsulta tipoConsulta = agendamento.getTipoConsulta();
        LocalDate dataDesejada = agendamento.getDataConsulta();

        List<Cronograma> eventos = cronogramaService.listarEventos();
        boolean tipoEDataDisponivel = eventos.stream()
                .anyMatch(evento -> evento.getTipoConsulta().equals(tipoConsulta) && evento.getData().equals(dataDesejada));

        if (tipoEDataDisponivel) {
            if (tipoConsulta != null && tipoConsulta.getVagasDisponiveis() > 0) {
                // Condição aceita, agendamento efetuado
                Agendamento novoAgendamento = repositorioAgendamento.save(agendamento);
                // Agora vamos atualizar as vagas disponíveis.
                tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis() - 1);
                return novoAgendamento;
            } else {
                // Tratamento para quando não houver vagas disponíveis
                throw new RuntimeException("Não há vagas disponíveis para o tipo de consulta selecionado.");
            }
        } else {
            // Tratamento para quando o tipo de consulta não estiver disponível na data desejada
            throw new RuntimeException("O tipo de consulta selecionado não está disponível na data desejada.");
        }
    }*/
	
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
	
	//implementei amesma logica do save a diferença é que eu adicionei mais uma vaga
	@Transactional
	public void deleteAgendamento(Agendamento agendamento) {
		TipoDeConsulta tipoConsulta=agendamento.getTipoConsulta();
		if(tipoConsulta!=null && tipoConsulta.getVagasDisponiveis()>0) {
			repositorioAgendamento.delete(agendamento);
			tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis()+1);
		}
	}
	
}