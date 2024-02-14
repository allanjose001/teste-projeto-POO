package com.api.agendafacil.services;
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
		//verificar se tem vagas disponiveis para o tipo do agendamento
		TipoDeConsulta tipoConsulta=agendamento.getTipoConsulta();
		if(tipoConsulta!=null && tipoConsulta.getVagasDisponiveis()>0) {
			//condição aceita, agendamento efetuuado
			Agendamento novoAgendamento=repositorioAgendamento.save(agendamento);
			//agora vamos atualizar as vagas disponiveis.
			tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis()-1);
			return novoAgendamento;
		}else {
			//! precisa implementar o throw qui em vez de deixar esse Sysout <fazer um tratamento personalizado>
			System.out.println("não tem vagas disponiveis");
		}
		return repositorioAgendamento.save(agendamento);
	}
	public List<Agendamento> getAllAgendamento() {
		return repositorioAgendamento.findAll();
	}
	
	public Optional<Agendamento> findAgendamentoById(UUID id) {
		return repositorioAgendamento.findById(id);
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
