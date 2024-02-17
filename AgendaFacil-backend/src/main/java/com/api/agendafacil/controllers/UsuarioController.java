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

import com.api.agendafacil.dtos.UsuarioDto;
import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.Usuario;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	@Autowired
	private Facade facade;
	
	@PostMapping
	public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {				
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveUsuario(usuario));
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getTodosUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllUsuario());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUmUsuario(@PathVariable(value = "id") UUID id){
		Optional<Usuario> usuarioOptional = facade.findUsuarioById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "id") UUID id){
		Optional<Usuario> usuarioOptional = facade.findUsuarioById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		facade.deleteUsuario(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario removido com sucesso");
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") UUID id, @RequestBody @Valid UsuarioDto usuarioDto){
		Optional<Usuario> usuarioOptional = facade.findUsuarioById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		usuario.setId(usuarioOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(facade.saveUsuario(usuario));
	}	
	
}
