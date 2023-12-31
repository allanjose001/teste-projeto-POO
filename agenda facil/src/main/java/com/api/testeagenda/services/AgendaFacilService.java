package com.api.testeagenda.services;

/*classe de serviço
 *A classe recebe uma instância de RepositorioUBS no construtor. servindo de injeção de dependência.
 *Isso permite que a classe utilize o repositório sem criar diretamente a instância. 
 *Isso facilita a substituição de implementações ou mocks para testes.
 */

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.testeagenda.models.AgendaFacilModelo;
import com.api.testeagenda.repositories.RepositorioUBS;

import jakarta.transaction.Transactional;

@Service
public class AgendaFacilService {

	final RepositorioUBS repositorioUBS;
	
	public AgendaFacilService(RepositorioUBS repositorioUBS) {
		this.repositorioUBS = repositorioUBS;
	}
	
	@Transactional
	public AgendaFacilModelo save(AgendaFacilModelo agendaFacilModel) {
		return repositorioUBS.save(agendaFacilModel);
	}

	public boolean existsByCodigoUBS(String codigoUBS) {
		return repositorioUBS.existsByCodigoUBS(codigoUBS);
	}

	public boolean existsByNomeUBS(String nomeUBS) {
		return repositorioUBS.existsByNomeUBS(nomeUBS);
	}

	public List<AgendaFacilModelo> findAll() {
		return repositorioUBS.findAll();
	}

	public Optional<AgendaFacilModelo> findById(UUID id) {
		return repositorioUBS.findById(id);
	}

	@Transactional
	public void delete(AgendaFacilModelo agendaFacilModelo) {
		repositorioUBS.delete(agendaFacilModelo);
	}
}
