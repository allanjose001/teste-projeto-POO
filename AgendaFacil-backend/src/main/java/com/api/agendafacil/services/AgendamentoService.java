package com.api.agendafacil.services;
import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.dtos.CronogramaDto;
import com.api.agendafacil.enums.TipoDeConsulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Agendamento;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.repositories.RepositorioAgendamento;

import jakarta.transaction.Transactional;
import com.api.agendafacil.services.CronogramaService;

@Service
public class AgendamentoService {

	@Autowired
	private RepositorioAgendamento repositorioAgendamento;
	
	@Autowired
    private CronogramaService cronogramaService;

	
	@Transactional
    public Agendamento save(Agendamento agendamento) {
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
    }

    // Outros métodos do serviço
	public List<Agendamento> findAll() {
		return repositorioAgendamento.findAll();
	}
	
	public Optional<Agendamento> findById(UUID id) {
		return repositorioAgendamento.findById(id);
	}
	public Agendamento updateAgendamento(UUID id, AgendamentoDto agendamentoDto) {
		Optional<Agendamento> agendamentoOptional = findById(id);
	    if (agendamentoOptional.isPresent()) {
	       Agendamento agendamento = agendamentoOptional.get();
	            agendamento.setTipoConsulta(agendamentoDto.getTipoConsulta());
	            agendamento.setDataConsulta(agendamentoDto.getDataConsulta());
	            return repositorioAgendamento.save(agendamento);
	     } else {
	            // Se o agendamento não for encontrado, você pode lançar uma exceção ou retornar null, dependendo dos requisitos da sua aplicação
	         return null;
	        }
	}
	
	//implementei amesma logica do save a diferença é que eu adicionei mais uma vaga
	@Transactional
	public void delete(Agendamento agendamento) {
		TipoDeConsulta tipoConsulta=agendamento.getTipoConsulta();
		if(tipoConsulta!=null && tipoConsulta.getVagasDisponiveis()>0) {
			repositorioAgendamento.delete(agendamento);
			tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis()+1);
		}
		
	}
	
}
