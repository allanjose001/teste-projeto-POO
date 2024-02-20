package com.api.agendafacil.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.enums.TipoDeConsulta;
import com.api.agendafacil.models.Agendamento;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.models.Endereco;
import com.api.agendafacil.models.TipoConsulta;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.models.Usuario;
import com.api.agendafacil.repositories.RepositorioAgendamento;
import com.api.agendafacil.services.AgendamentoService;
import com.api.agendafacil.services.AuthUsuarioService;
import com.api.agendafacil.services.CronogramaService;
import com.api.agendafacil.services.EnderecoService;
import com.api.agendafacil.services.UBSService;
import com.api.agendafacil.services.UsuarioService;

import jakarta.transaction.Transactional;

@Service
public class Facade {

	
	//UBS--------------------------------------------------------------------------------------------------
	@Autowired
	private UBSService ubsService;
	
	public UBS saveUBS(UBS ubs) {
		return ubsService.saveUBS(ubs);
	}
	
	public List<UBS> getAllUBS() {
		return ubsService.getAllUBS();
	}

	public Optional<UBS> findUBSById(UUID id) {
		return ubsService.findUBSById(id);
	}

	public void deleteUBS(UBS ubs) {
		ubsService.deleteUBS(ubs);
	}
	
	//metodos como existsByNome não são necessarios aqui
	//Endereco----------------------------------------------------------
	private EnderecoService enderecoService;
	
	public Endereco saveEndereco(Endereco endereco) {
		//caso catch do erro (precisa ser pensado a respeito)
		/*try {
			return ubsService.save(ubs);
		} catch (Exception e) {
			throw new RuntimeException("erro ao salvar", e)
		}
		*/
		return enderecoService.saveEndereco(endereco);
	}
	
	public List<Endereco> getAllEndereco() {
		return enderecoService.getAllEndereco();
	}

	public Optional<Endereco> findEnderecoById(UUID id) {
		return enderecoService.findEnderecoById(id);
	}

	public void deleteEndereco(Endereco endereco) {
		enderecoService.deleteEndereco(endereco);
	}
	
	//Agendamento------------------------------------------------------
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@Autowired
	private RepositorioAgendamento repositorioAgendamento;
	
	/*
	public Agendamento saveAgendamento(Agendamento agendamento) {
		return agendamentoService.saveAgendamento(agendamento);
	}
	*/
	public Agendamento saveAgendamento(Agendamento agendamento) {
		TipoDeConsulta tipoConsulta = agendamento.getTipoConsulta();
		LocalDate dataDesejada = agendamento.getDataConsulta();
		
		System.out.println("vagas disponiveis: "+ TipoDeConsulta.MEDICO.getVagasDisponiveis());
		
		List<Cronograma> eventos = cronogramaService.listarEventos();		
		boolean tipoEDataDisponivel = eventos.stream()
				.anyMatch(evento -> evento.getTipoConsulta().equals(tipoConsulta) && evento.getData().equals(dataDesejada));
				//verifica se o tipo de consulta é atendido naquele dia
		if (tipoEDataDisponivel) {
			if (tipoConsulta != null && tipoConsulta.getVagasDisponiveis() > 0) {
				//verifica se tem vagas disponiveis
				
				tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis() - 1);
				
				Agendamento novoAgendamento = repositorioAgendamento.save(agendamento);
				System.out.println("vagas disponiveis, depois de salvar: "+ TipoDeConsulta.MEDICO.getVagasDisponiveis());
				return novoAgendamento;
			} else {
				throw new RuntimeException("neste dia não há mais vagas disponiveis para esta consulta");
			}
		} else {
			throw new RuntimeException("Essa consulta não é realizada no dia selecionado");
		}
	}
	
	public List<Agendamento> getAllAgendamento() {
		return agendamentoService.getAllAgendamento();
	}
	
	public Optional<Agendamento> findAgendamentoById(UUID id) {
		return agendamentoService.findAgendamentoById(id);
	}
	
	public Agendamento updateAgendamento(UUID id, AgendamentoDto agendamentoDto) {
		return agendamentoService.updateAgendamento(id, agendamentoDto);
	}
				
	@Transactional
	public void deleteAgendamento(Agendamento agendamento) {
		agendamentoService.deleteAgendamento(agendamento);
	}
	
	//Cronograma--------------------------------------------------------------------
	
	@Autowired
	private CronogramaService cronogramaService;
	
	public void adicionarEvento(Cronograma cronograma) {
		cronogramaService.adicionarEvento(cronograma);
	}
	
	public List<Cronograma> listarEventos() {
		return cronogramaService.listarEventos();
	}
	
	public void atualizarEvento(UUID id, Cronograma novoCronograma) {
		cronogramaService.atualizarEvento(id, novoCronograma);
	}
	
	//Usuario--------------------------------------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;
	
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioService.saveUsuario(usuario);
	}
	
	public List<Usuario> getAllUsuario() {
		return usuarioService.getAllUsuario();
	}
	
	public Optional<Usuario> findUsuarioById(UUID id) {
		return usuarioService.findUsuarioById(id);
	}
	
	public void deleteUsuario(Usuario usuario) {
		usuarioService.deleteUsuario(usuario);
	}
	//AuthUsuario------------------------------------------------------------------------------
	
	@Autowired
	private AuthUsuarioService authUsuarioService;
	
	public Usuario autenticarUsuario(String nome, String senha) {
		return authUsuarioService.autenticarUsuario(nome,senha);
	}
	

	
	
	
	
	
	
	
	
	
	
}
