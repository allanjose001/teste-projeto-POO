package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.ProfissionalSaude;
import com.api.agendafacil.repositories.RepositorioProfissionalSaude;

import jakarta.transaction.Transactional;

@Service
public class ProfissionalSaudeService implements ProfissionalSaudeServiceInterface {
	
	@Autowired
	private RepositorioProfissionalSaude repositorioProfissionalSaude;
	
	@Transactional
	public ProfissionalSaude saveProfissionalSaude(ProfissionalSaude profissionalSaude) {
		return repositorioProfissionalSaude.save(profissionalSaude);
	}

	public List<ProfissionalSaude> getAllProfissionalSaude() {
		return repositorioProfissionalSaude.findAll();
	}

	public Optional<ProfissionalSaude> findProfissionalSaudeById(UUID id) {
		return repositorioProfissionalSaude.findById(id);
		//ideal adicionar or.ElseThrow()
	}

	@Transactional
	public void deleteProfissionalSaude(ProfissionalSaude profissionalSaude) {
		repositorioProfissionalSaude.delete(profissionalSaude);
		//ideal adicionar or.ElseThrow()
	}

}
