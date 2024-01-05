package com.api.agendafacil.controllers;

/*classe controladora do banco de dados
 * aqui se encontra todos os comandos de manipulação do banco de dados
*/
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
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

import com.api.agendafacil.dtos.AgendaFacilDto;
import com.api.agendafacil.models.AgendaFacilModelo;
import com.api.agendafacil.services.AgendaFacilService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agenda-facil")
public class AgendaFacilController {

	//equivalente ao @autowired
	final AgendaFacilService agendaFacilService;

	public AgendaFacilController(AgendaFacilService agendaFacilService) {
		this.agendaFacilService = agendaFacilService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveAgendaFacil(@RequestBody @Valid AgendaFacilDto agendaFacilDto) {		
		if(agendaFacilService.existsByNomeUBS(agendaFacilDto.getNomeUBS())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Nome de UBS em uso");
		}
		
		var agendaFacilModel = new AgendaFacilModelo();
		BeanUtils.copyProperties(agendaFacilDto, agendaFacilModel);
		agendaFacilModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaFacilService.save(agendaFacilModel));
	}
	
	@GetMapping
	public ResponseEntity<List<AgendaFacilModelo>> getTodasUBS(){
		return ResponseEntity.status(HttpStatus.OK).body(agendaFacilService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmaUBS(@PathVariable(value = "id") UUID id){
		Optional<AgendaFacilModelo> agendaFacilModeloOptional = agendaFacilService.findById(id);
		if (!agendaFacilModeloOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(agendaFacilModeloOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUBS(@PathVariable(value = "id") UUID id){
		Optional<AgendaFacilModelo> agendaFacilModeloOptional = agendaFacilService.findById(id);
		if (!agendaFacilModeloOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		agendaFacilService.delete(agendaFacilModeloOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("UBS removida com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUBS(@PathVariable(value = "id") UUID id, @RequestBody @Valid AgendaFacilDto agendaFacilDto){
		Optional<AgendaFacilModelo> agendaFacilModeloOptional = agendaFacilService.findById(id);
		if (!agendaFacilModeloOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		var agendaFacilModelo = new AgendaFacilModelo();
		BeanUtils.copyProperties(agendaFacilDto, agendaFacilModelo);
		agendaFacilModelo.setId(agendaFacilModeloOptional.get().getId());
		agendaFacilModelo.setRegistrationDate(agendaFacilModeloOptional.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(agendaFacilService.save(agendaFacilModelo));
	}	
}
