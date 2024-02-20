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

import com.api.agendafacil.dtos.UBSDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.UBS;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/agenda-facil")
public class UBSController {

	@Autowired
	private Facade facade;;
	
	@PostMapping
	public ResponseEntity<Object> saveUBS(@RequestBody @Valid UBSDto ubsDto) {		
		var ubs = new UBS();
		BeanUtils.copyProperties(ubsDto, ubs);
		ubs.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveUBS(ubs));
	}

	@GetMapping
	public ResponseEntity<List<UBS>> getTodasUBS(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllUBS());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmaUBS(@PathVariable(value = "id") UUID id){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ubsOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUBS(@PathVariable(value = "id") UUID id){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		facade.deleteUBS(ubsOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("UBS removida com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUBS(@PathVariable(value = "id") UUID id, @RequestBody @Valid UBSDto ubsDto){
		Optional<UBS> ubsOptional = facade.findUBSById(id);
		if (!ubsOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UBS não encontrada");
		}
		var ubs = new UBS();
		BeanUtils.copyProperties(ubsDto, ubs);
		ubs.setId(ubsOptional.get().getId());
		ubs.setRegistrationDate(ubsOptional.get().getRegistrationDate());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveUBS(ubs));
	}	
}
