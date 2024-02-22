package com.api.agendafacil.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.agendafacil.dtos.CronogramaDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Cronograma;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cronograma")
public class CronogramaController {
	
	@Autowired
    private Facade facade;

	@PostMapping
	public ResponseEntity<Object> saveCronograma(@RequestBody @Valid CronogramaDto cronogramaDto) {				
		var cronograma = new Cronograma();
		BeanUtils.copyProperties(cronogramaDto, cronograma);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.adicionarCronograma(cronograma));
	}
	
	@GetMapping
	public ResponseEntity<List<Cronograma>> getTodosCronogramas(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.listarCronograma());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateCronograma(@PathVariable(value = "id") UUID id, @RequestBody @Valid CronogramaDto cronogramaDto){
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
	public ResponseEntity<Object> deleteCronograma(@PathVariable(value = "id") UUID id){
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
