package com.api.agendafacil.facade;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Endereco;
import com.api.agendafacil.models.ProfissionalSaude;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.services.EnderecoService;
import com.api.agendafacil.services.ProfissionalSaudeService;
import com.api.agendafacil.services.UBSService;

@Service
public class Facade {
	
	//UBS--------------------------------------------------------------------------------------------------
	@Autowired
	private UBSService ubsService;
	
	public UBS saveUBS(UBS ubs) {
		//caso catch do erro (precisa ser pensado a respeito)
		/*try {
			return ubsService.save(ubs);
		} catch (Exception e) {
			throw new RuntimeException("erro ao salvar", e)
		}
		*/
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
	
	//ProfissionalSaude------------------------------------------------
	@Autowired
	private ProfissionalSaudeService profissionalSaudeService;
	
	public ProfissionalSaude saveProfissionalSaude(ProfissionalSaude profissionalSaude) {
		//caso catch do erro (precisa ser pensado a respeito)
		/*try {
			return ubsService.save(ubs);
		} catch (Exception e) {
			throw new RuntimeException("erro ao salvar", e)
		}
		*/
		return profissionalSaudeService.saveProfissionalSaude(profissionalSaude);
	}
	
	public List<ProfissionalSaude> getAllProfissionalSaude() {
		return profissionalSaudeService.getAllProfissionalSaude();
	}

	public Optional<ProfissionalSaude> findProfissionalSaudeById(UUID id) {
		return profissionalSaudeService.findProfissionalSaudeById(id);
	}

	public void deleteProfissionalSaude(ProfissionalSaude profissionalSaude) {
		profissionalSaudeService.deleteProfissionalSaude(profissionalSaude);
	}
	
	
}
