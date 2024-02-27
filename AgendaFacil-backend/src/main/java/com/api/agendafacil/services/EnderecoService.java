package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Endereco;
import com.api.agendafacil.repositories.RepositorioEndereco;

import jakarta.transaction.Transactional;

@Service
public class EnderecoService implements EnderecoServiceInterface {

	@Autowired
	private RepositorioEndereco repositorioEndereco;
	
	@Transactional
	public Endereco saveEndereco(Endereco endereco) {
		return repositorioEndereco.save(endereco);
	}

	public List<Endereco> getAllEndereco() {
		return repositorioEndereco.findAll();
	}

	public Optional<Endereco> findEnderecoById(UUID id) {
		return repositorioEndereco.findById(id);
	}

	@Transactional
	public void deleteEndereco(Endereco endereco) {
		repositorioEndereco.delete(endereco);
	}
	
}
