package com.api.agendafacil.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agendafacil.dtos.ProfissionalSaudeDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.ProfissionalSaude;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/profissional-saude")
public class ProfissionalSaudeController {

	@Autowired
	private Facade facade;;
	
	@PostMapping
	public ResponseEntity<Object> saveProfissionalSaude(@RequestBody @Valid ProfissionalSaudeDto profissionalSaudeDto) {		
		var profissionalSaude = new ProfissionalSaude();
		BeanUtils.copyProperties(profissionalSaudeDto, profissionalSaude);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveProfissionalSaude(profissionalSaude));
	}
	
	@GetMapping
	public ResponseEntity<List<ProfissionalSaude>> getTodasProfissionalSaude(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllProfissionalSaude());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmProfissionalSaude(@PathVariable(value = "id") UUID id){
		Optional<ProfissionalSaude> profissionalSaudeOptional = facade.findProfissionalSaudeById(id);
		if (!profissionalSaudeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional de Saude não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(profissionalSaudeOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProfissionalSaude(@PathVariable(value = "id") UUID id){
		Optional<ProfissionalSaude> profissionalSaudeOptional = facade.findProfissionalSaudeById(id);
		if (!profissionalSaudeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional de Saude não encontrado");
		}
		facade.deleteProfissionalSaude(profissionalSaudeOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Profissional de Saude removido com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProfissionalSaude(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProfissionalSaudeDto profissionalSaudeDto){
		Optional<ProfissionalSaude> profissionalSaudeOptional = facade.findProfissionalSaudeById(id);
		if (!profissionalSaudeOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profissional de Saude não encontrado");
		}
		var profissionalSaude = new ProfissionalSaude();
		BeanUtils.copyProperties(profissionalSaudeDto, profissionalSaude);
		profissionalSaude.setId(profissionalSaudeOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveProfissionalSaude(profissionalSaude));
	}	
}