package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Consulta;
import com.api.agendafacil.repositories.RepositorioConsulta;
import jakarta.transaction.Transactional;

@Service
public class ConsultaService {

	@Autowired
	private RepositorioConsulta repositorioConsulta;
	
	@Transactional
	public Consulta save(Consulta consulta) {
		return repositorioConsulta.save(consulta);
	}

	public List<Consulta> findAll() {
		return repositorioConsulta.findAll();
	}

	public Optional<Consulta> findById(UUID id) {
		return repositorioConsulta.findById(id);
	}

	@Transactional
	public void delete(Consulta consulta) {
		repositorioConsulta.delete(consulta);
	}
}