package com.api.agendafacil.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.agendafacil.dtos.CronogramaDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Cronograma;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cronograma")

@Tag(name = "Cronogramas", description = "API para manipulação de cronogramas")
public class CronogramaController {
	
	@Autowired
    private Facade facade;

	@PostMapping
    @Operation(summary = "Salvar Cronograma", description = "esse endpoint Salva cronograma .")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário autenticado com sucesso."),
        @ApiResponse(responseCode = "400", description = "Autenticação falhou. Nome de usuário ou senha inválidos."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
       
    })
	public ResponseEntity<Object> saveCronograma(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto CronogramaDto a ser salvo", required = true, content = @Content(schema = @Schema(implementation = CronogramaDto.class)))@RequestBody @Valid CronogramaDto cronogramaDto) {				
		var cronograma = new Cronograma();
		BeanUtils.copyProperties(cronogramaDto, cronograma);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.adicionarCronograma(cronograma));
	}
	
	@GetMapping
	 @Operation(summary = "Obter todos os cronogramas", description = "Esse endpoint Retorna todos os cronogramas cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cronogramas retornada com sucesso."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<List<Cronograma>> getTodosCronogramas(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.listarCronograma());
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Cronograma", description = "Esse endpoint Atualiza um cronograma existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cronograma atualizado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Cronograma não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> updateCronograma(@PathVariable(value = "id") 
    @Parameter(description = "ID do cronograma a ser atualizado", required = true)
	UUID id,
	@RequestBody @Valid  
	@Parameter(description = "Objeto CronogramaDto contendo os dados a serem atualizados", required = true, schema = @Schema(implementation = CronogramaDto.class))  
	CronogramaDto cronogramaDto){
		
		Optional<Cronograma> cronogramaOptional = facade.listarCronogramaPorId(id);
		if (!cronogramaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cronograma não encontrado");
		}
		var cronograma = new Cronograma();
		BeanUtils.copyProperties(cronogramaDto, cronograma);
		cronograma.setId(cronogramaOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.adicionarCronograma(cronograma));
	}	
	
	@DeleteMapping("/{id}")
	 @Operation(summary = "Excluir Cronograma", description = "Esse endpoint Exclui um cronograma existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cronograma excluído com sucesso."),
        @ApiResponse(responseCode = "404", description = "Cronograma não encontrado."),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> deleteCronograma( @PathVariable(value = "id")  @Parameter(description = "ID do cronograma a ser excluído", required = true)  UUID id){
		Optional<Cronograma> cronogramaOptional = facade.listarCronogramaPorId(id);
		if (!cronogramaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cronograma não encontrado");
		}
		facade.deleteCronograma(cronogramaOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cronograma removido com sucesso");
	}
	
	
	/*
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmCronograma(@PathVariable(value = "id") UUID id){
		Optional<Cronograma> cronogramaOptional = facade.findCronogramaById(id);
		if (!CronogramaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(CronogramaOptional.get());
	}
	 */
	
	
	/*
    @PostMapping
    public ResponseEntity<Object> adicionarCronograma(@RequestBody @Valid Cronograma cronograma) {
        cronogramaService.adicionarCronograma(cronograma);
        return new ResponseEntity<>("Evento adicionado ao cronograma com sucesso", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cronograma>> listarCronograma() {
        List<Cronograma> eventos = facade.listarCronograma();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    // atualizar um evento existente
    @PutMapping("{id}")
    public ResponseEntity<?> atualizarCronograma(@PathVariable UUID id, @RequestBody Cronograma cronograma) {
        facade.atualizarCronograma(id, cronograma);
        return new ResponseEntity<>("Evento atualizado com sucesso", HttpStatus.OK);
    }
    */
}