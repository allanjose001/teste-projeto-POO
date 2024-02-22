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
	
	public Agendamento saveAgendamento(Agendamento agendamento) {
		TipoDeConsulta tipoConsulta = agendamento.getTipoConsulta();
		LocalDate dataDesejada = agendamento.getDataConsulta();
		//instancia os tipos necessarios para fazer as operações
		UBS ubs = agendamento.getUbs();
		//acessa a UBS pelo Id que está dentro de Agendamento
		System.out.println("vagas disponiveis: "+ tipoConsulta.getVagasDisponiveis());
		
		List<Cronograma> cronogramas = cronogramaService.listarCronogramaPorUBS(ubs);
		//lista somente a UBS desejada
		boolean tipoEDataDisponivel = cronogramas.stream()
				.anyMatch(cronograma -> cronograma.getDiasSemana().contains(dataDesejada.getDayOfWeek().toString())
						&& cronograma.getTiposConsulta().contains(tipoConsulta));
				//verifica se o tipo de consulta é atendido naquele dia da semana
		
		if (tipoEDataDisponivel) {
			if (tipoConsulta != null && tipoConsulta.getVagasDisponiveis() > 0) {
				tipoConsulta.setVagasDisponiveis(tipoConsulta.getVagasDisponiveis() - 1);
				//verifica se tem vagas disponiveis e tira 1 vaga
			
				Agendamento novoAgendamento = agendamentoService.saveAgendamento(agendamento);
	
				System.out.println("vagas disponiveis, depois de salvar: "+ tipoConsulta.getVagasDisponiveis());
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
		return cronogramaService.adicionarCronograma(cronograma);
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
