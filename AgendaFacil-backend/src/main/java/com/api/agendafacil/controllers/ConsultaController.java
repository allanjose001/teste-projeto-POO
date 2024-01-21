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

import com.api.agendafacil.dtos.ConsultaDto;
import com.api.agendafacil.models.Consulta;
import com.api.agendafacil.services.ConsultaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ResponseEntity<Object> saveConsulta(@RequestBody @Valid ConsultaDto consultaDto) {		
		var consulta = new Consulta();
		BeanUtils.copyProperties(consultaDto, consulta);
		//consulta.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.save(consulta));
	}
	
	@GetMapping
	public ResponseEntity<List<Consulta>> getTodasConsultas(){
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmaConsulta(@PathVariable(value = "id") UUID id){
		Optional<Consulta> consultaOptional = consultaService.findById(id);
		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(consultaOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteConsulta(@PathVariable(value = "id") UUID id){
		Optional<Consulta> consultaOptional = consultaService.findById(id);
		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
		}
		consultaService.delete(consultaOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Consulta removida com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateConsulta(@PathVariable(value = "id") UUID id, @RequestBody @Valid ConsultaDto consultaDto){
		Optional<Consulta> consultaOptional = consultaService.findById(id);
		if (!consultaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta não encontrada");
		}
		var consulta = new Consulta();
		BeanUtils.copyProperties(consultaDto, consulta);
		consulta.setId(consultaOptional.get().getId());
		//consulta.setRegistrationDate(consultaOptional.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(consultaService.save(consulta));
	}	
}