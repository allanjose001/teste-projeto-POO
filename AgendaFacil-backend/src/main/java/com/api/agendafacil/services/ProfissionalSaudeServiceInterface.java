package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.api.agendafacil.models.ProfissionalSaude;

public interface ProfissionalSaudeServiceInterface {
	ProfissionalSaude saveProfissionalSaude(ProfissionalSaude profissionalSaude);
	List<ProfissionalSaude> getAllProfissionalSaude();
	Optional<ProfissionalSaude> findProfissionalSaudeById(UUID id);
	void deleteProfissionalSaude(ProfissionalSaude profissionalSaude);

}
