package com.api.agendafacil.services;

/*classe de serviço
 *A classe recebe uma instância de RepositorioUBS no construtor. servindo de injeção de dependência.
 *Isso permite que a classe utilize o repositório sem criar diretamente a instância. 
 *Isso facilita a substituição de implementações ou mocks para testes.
 */

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.UBS;
import com.api.agendafacil.repositories.RepositorioUBS;

import jakarta.transaction.Transactional;

@Service
public class UBSService {

	@Autowired
	private RepositorioUBS repositorioUBS;
	
	@Transactional
	public UBS save(UBS ubs) {
		return repositorioUBS.save(ubs);
	}

	public boolean existsByNomeUBS(String nomeUBS) {
		return repositorioUBS.existsByNomeUBS(nomeUBS);
	}

	public List<UBS> findAll() {
		return repositorioUBS.findAll();
	}

	public Optional<UBS> findById(UUID id) {
		return repositorioUBS.findById(id);
	}

	@Transactional
	public void delete(UBS ubs) {
		repositorioUBS.delete(ubs);
	}
}
