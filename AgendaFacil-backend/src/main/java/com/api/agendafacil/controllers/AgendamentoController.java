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

import com.api.agendafacil.dtos.AgendamentoDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Agendamento;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse; 

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agendamento")
@Tag(name = "Agendamento", description = "API para manipulação de Agendamento")
public class AgendamentoController {

	@Autowired
	private Facade facade;
	
	@PostMapping
    @Operation(summary = "Salva um agendamento", description = "Este endpoint salva um novo agendamento.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso",
        		content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
	public ResponseEntity<Object> saveAgendamento( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto AgendamentoDto contendo os detalhes do novo agendamento", required = true) @RequestBody @Valid AgendamentoDto agendamentoDto) {				
		var agendamento = new Agendamento();
		BeanUtils.copyProperties(agendamentoDto, agendamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveAgendamento(agendamento));
	}
	 
	@GetMapping
	@Operation(summary = "Listar todos os agendamentos", description = "Este endpoint retorna uma lista de todos os agendamentos cadastrados.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso", 
	    		content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
	    @ApiResponse(responseCode = "404", description = "Nenhum agendamento encontrado"),
	    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<List<Agendamento>> getTodosAgendamentos(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllAgendamento());
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Buscar um agendamento pelo ID", description = "Este endpoint retorna um agendamento com base no ID fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Agendamento encontrado com sucesso", 
	    		content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
	    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
	    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Object> getUmAgendamento(  @Parameter(description = "ID do agendamento", required = true) @PathVariable(value = "id") UUID id){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(agendamentoOptional.get());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um agendamento pelo ID", description = "Este endpoint remove um agendamento com base no ID fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Agendamento removido com sucesso",
	    		content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
	    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
	    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Object> deleteAgendamento(  @Parameter(description = "ID do agendamento a ser removido", required = true) @PathVariable(value = "id") UUID id){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		facade.deleteAgendamento(agendamentoOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Agendamento removido com sucesso");
	}
	
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um agendamento pelo ID", description = "Este endpoint atualiza um agendamento existente com base no ID fornecido.")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Agendamento atualizado com sucesso",
	    		content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
	    @ApiResponse(responseCode = "404", description = "Agendamento não encontrado"),
	    @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	})
	public ResponseEntity<Object> updateAgendamento(  @Parameter(description = "ID do agendamento a ser atualizado", required = true) @PathVariable(value = "id") UUID id, @RequestBody @Valid AgendamentoDto agendamentoDto){
		Optional<Agendamento> agendamentoOptional = facade.findAgendamentoById(id);
		if (!agendamentoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Agendamento não encontrado");
		}
		Agendamento agendamento = agendamentoOptional.get();
		facade.updateAgendamento(id, agendamentoDto);
		//agendamento.setRegistrationDate(ubsOptional.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(agendamento);
	}
}