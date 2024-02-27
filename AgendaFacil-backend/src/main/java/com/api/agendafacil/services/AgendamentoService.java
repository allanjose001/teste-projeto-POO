package com.api.agendafacil.services;
/**
 * Serviço responsável por lidar com operações relacionadas a agendamentos.
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 * 
 */
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
	
	/**
     * Salva um novo agendamento.
     *
     * @param agendamento O agendamento a ser salvo.
     * @return O agendamento salvo.
     */
	@Transactional
    public Agendamento saveAgendamento(Agendamento agendamento) {  
		return repositorioAgendamento.save(agendamento);
    }
	
	 /**
     * Obtém todos os agendamentos.
     *
     * @return Uma lista de todos os agendamentos.
     */
    // Outros métodos do serviço
	public List<Agendamento> getAllAgendamento() {
		return repositorioAgendamento.findAll();
	}
	
	/**
     * Encontra um agendamento pelo ID.
     *
     * @param id O ID do agendamento a ser encontrado.
     * @return Um Optional contendo o agendamento encontrado, se presente.
     */
	public Optional<Agendamento> findAgendamentoById(UUID id) {
		return repositorioAgendamento.findById(id);
	}
	
	/**
     * Atualiza um agendamento existente com base no ID e nos dados do DTO fornecido.
     *
     * @param id O ID do agendamento a ser atualizado.
     * @param agendamentoDto Os dados do agendamento a serem atualizados.
     * @return O agendamento atualizado.
     */
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
	
	 /**
     * Exclui um agendamento e libera uma vaga no tipo de consulta associado.
     *
     * @param agendamento O agendamento a ser excluído.
     */
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