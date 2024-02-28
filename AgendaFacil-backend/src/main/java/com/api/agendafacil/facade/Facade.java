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
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.models.Usuario;
import com.api.agendafacil.services.AgendamentoService;
import com.api.agendafacil.services.AuthUsuarioService;
import com.api.agendafacil.services.CronogramaService;
import com.api.agendafacil.services.EnderecoService;
import com.api.agendafacil.services.UBSService;
import com.api.agendafacil.services.UsuarioService;

import jakarta.transaction.Transactional;

/**
 * classe fachada do sistema, serve para fazer uma ligação entre classes de negocios
 * isso ajuda para que todas as classes service estejam ligadas á apenas uma classe
 * e então basta chama-los nos controllers pela fachada ao invés de chamar pelo Service
 * o que facilita a manutenção do codigo
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 */
@Service
public class Facade {

	
	//UBS--------------------------------------------------------------------------------------------------
	@Autowired
	private UBSService ubsService;
	
	public UBS saveUBS(UBS ubs) {
		//tenta salvar ubs, se receber alguma exceção captura ela e a retorna
		//para ser chamada no controller
		try {
			return ubsService.saveUBS(ubs);	
		} catch (Exception e) {
			throw new RuntimeException("Erro ao salvar a UBS", e);
		}
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

	//Endereco----------------------------------------------------------
	private EnderecoService enderecoService;
	
	public Endereco saveEndereco(Endereco endereco) {
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
	
	public Agendamento saveAgendamento(Agendamento agendamento) {
		//instancia os tipos necessarios para fazer as operações
		TipoDeConsulta tipoConsulta = agendamento.getTipoConsulta();
		LocalDate dataDesejada = agendamento.getDataConsulta();
		//acessa a UBS pelo Id que está dentro de Agendamento
		UBS ubs = agendamento.getUbs();

		//lista somente a UBS desejada
		List<Cronograma> cronogramas = cronogramaService.listarCronogramaPorUBS(ubs);
		//verifica no cronograma se o tipo de consulta escolhido é atendido naquele dia da semana
		boolean tipoEDataDisponivel = cronogramas.stream()
				.anyMatch(cronograma -> cronograma.getDiasSemana().contains(dataDesejada.getDayOfWeek().toString())
						&& cronograma.getTiposConsulta().contains(tipoConsulta));
		
		//se o tipo de consulta não está no cronograma retorna uma exception
		if (tipoEDataDisponivel) {
			//verifica se ainda tem vaga disponivel para aquele dia, se não retorna uma exception
			if (tipoConsulta != null && tipoConsulta.getVagasDisponiveis() > 0) {
				tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis() - 1);
			
				Agendamento novoAgendamento = agendamentoService.saveAgendamento(agendamento);
	
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
	
	public Cronograma adicionarCronograma(Cronograma cronograma) {
		try {
			return cronogramaService.adicionarCronograma(cronograma);
		}catch(Exception e) {
			throw new RuntimeException("Erro ao adicionar cronograma ", e);
		}
	}
	
	public Optional<Cronograma> listarCronogramaPorId(UUID id) {
		return cronogramaService.listarCronogramaPorId(id);
	}
	
	public List<Cronograma> listarCronograma() {
		return cronogramaService.listarCronograma();
	}
	
	public Cronograma atualizarCronograma(UUID id, Cronograma novoCronograma) {
		return cronogramaService.atualizarCronograma(id, novoCronograma);
	}
	
	public void deleteCronograma(Cronograma cronograma) {
		cronogramaService.deleteCronograma(cronograma);
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
