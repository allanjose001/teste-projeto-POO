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

import com.api.agendafacil.dtos.EnderecoDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Endereco;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private Facade facade;
	
	@PostMapping
	public ResponseEntity<Object> saveEndereco(@RequestBody @Valid EnderecoDto enderecoDto) {		
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDto, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveEndereco(endereco));
	}
	
	@GetMapping
	public ResponseEntity<List<Endereco>> getTodosEnderecos(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllEndereco());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmEndereco(@PathVariable(value = "id") UUID id){
		Optional<Endereco> enderecoOptional = facade.findEnderecoById(id);
		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(enderecoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEndereco(@PathVariable(value = "id") UUID id){
		Optional<Endereco> enderecoOptional = facade.findEnderecoById(id);
		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
		}
		facade.deleteEndereco(enderecoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Endereco removido com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id") UUID id, @RequestBody @Valid EnderecoDto enderecoDto){
		Optional<Endereco> enderecoOptional = facade.findEnderecoById(id);
		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrada");
		}
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDto, endereco);
		endereco.setId(enderecoOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveEndereco(endereco));
	}	
}
