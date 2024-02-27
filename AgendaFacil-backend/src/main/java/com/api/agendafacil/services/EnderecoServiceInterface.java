package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.Endereco;

public interface EnderecoServiceInterface {
	Endereco saveEndereco(Endereco endereco);
	List<Endereco> getAllEndereco();
	Optional<Endereco> findEnderecoById(UUID id);
	void deleteEndereco(Endereco endereco);
}
