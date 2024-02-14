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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Agendamento;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private Facade facade;
	
	@GetMapping
	public ResponseEntity<List<Agendamento>> getTodosAgendamentos(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllAgendamento());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmAgendamento(@PathVariable(value = "id") UUID id){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(agendamentoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAgendamento(@PathVariable(value = "id") UUID id){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		facade.deleteAgendamento(agendamentoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Agendamento removido com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAgendamento(@PathVariable(value = "id") UUID id, @RequestBody @Valid AgendamentoDto agendamentoDto){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		var agendamento = new Agendamento();
		BeanUtils.copyProperties(agendamentoDto, agendamento);
		agendamento.setId(agendamentoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveAgendamento(agendamento));
	}
}
