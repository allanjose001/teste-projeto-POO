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

import com.api.agendafacil.Exceptions.NomeUBSExistsException;
import com.api.agendafacil.dtos.UBSDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.UBS;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


/**
 * classe controladora de UBS
 * 
 * @author Alcielma
 * @author Allan
 * @author Pedro
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agenda-facil")
@Tag(name = "UBS", description = "API para manipulação de Endereço")
public class UBSController {

	@Autowired
	private Facade facade;

	@PostMapping
		@Operation(summary = "Salvar UBS", description = "Esse endpoint Cria uma nova UBS (Unidade Básica de Saúde).")
	    @ApiResponses(value = {
	        @ApiResponse(responseCode = "201", description = "UBS criada com sucesso."),
	        @ApiResponse(responseCode = "400", description = "Erro de validação."),
	        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
	    })
	public ResponseEntity<Object> saveUBS(@RequestBody @Valid UBSDto ubsDto) throws NomeUBSExistsException {		
		var ubs = new UBS();
		BeanUtils.copyProperties(ubsDto, ubs);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveUBS(ubs));
	}

	@GetMapping
	 @Operation(summary = "Obter Todas as UBS", description = "Esse endpoint Retorna todas as Unidades Básicas de Saúde cadastradas.")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de UBS retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<List<UBS>> getTodasUBS(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllUBS());
	}
	
	@GetMapping("/{id}")
	 @Operation(summary = "Obter uma UBS", description = "Esse endpoint Retorna uma Unidade Básica de Saúde com base no ID fornecido.")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS encontrada e retornada com sucesso."),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> getUmaUBS(@PathVariable(value = "id") UUID id){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ubsOptional.get());
	}
	
	@DeleteMapping("/{id}")
	 @Operation(summary = "Excluir UBS", description = "Esse endpoint Exclui uma Unidade Básica de Saúde com base no ID fornecido.")
     @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS removida com sucesso."),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> deleteUBS(@PathVariable(value = "id") UUID id){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		facade.deleteUBS(ubsOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("UBS removida com sucesso");
	}
	
	@PutMapping("/{id}")
    @Operation(summary = "Atualizar UBS", description = "Esse endpoint Atualiza uma Unidade Básica de Saúde com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "UBS atualizada com sucesso."),
        @ApiResponse(responseCode = "404", description = "UBS não encontrada."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> updateUBS(@PathVariable(value = "id") UUID id, @RequestBody @Valid UBSDto ubsDto){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		var ubs = new UBS();
		BeanUtils.copyProperties(ubsDto, ubs);
		ubs.setId(ubsOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveUBS(ubs));
	}	
}
