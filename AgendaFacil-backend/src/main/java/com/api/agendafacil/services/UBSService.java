package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.api.agendafacil.Exceptions.NomeUBSExistsException;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.repositories.RepositorioUBS;

import jakarta.transaction.Transactional;

/**
 * classe service de UBS, contem as regras basicas de negocio
 * para que uma UBS possa ser criada
 * 
 * @author Alcielma
 * @author Allan
 */
@Service
public class UBSService implements UBSServiceInterface{

	@Autowired
	private RepositorioUBS repositorioUBS;
	
	
	@Transactional
	//com essa anotação configuramos a nossa aplicação apenas para o admin ter acesso ao metodo
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UBS saveUBS(UBS ubs) throws NomeUBSExistsException{
		//verifica se o nome já esta em uso, se estiver retorna o erro
		if(existsByNomeUBS(ubs.getNomeUBS())) {
			throw new NomeUBSExistsException( "esse nome UBS já está em uso: "+ ubs.getNomeUBS());
		}
		return repositorioUBS.save(ubs);
	}

	public boolean existsByNomeUBS(String nomeUBS) {
		return repositorioUBS.existsByNomeUBS(nomeUBS);
	}

	public List<UBS> getAllUBS() {
		return repositorioUBS.findAll();
	}

	public Optional<UBS> findUBSById(UUID id) {
		return repositorioUBS.findById(id);
	}

	@Transactional
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUBS(UBS ubs) {
		repositorioUBS.delete(ubs);
	}
}
