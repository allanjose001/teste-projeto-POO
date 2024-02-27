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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * classe controladora de Usuario
 * 
 * @author Alcielma
 * @author Allan
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "API para manipulação de Usuário")
public class UsuarioController {
	
	
	@Autowired
	private Facade facade;
     
	@PostMapping
	@Operation(summary = "Salvar um usuário", description = "Este endpoint salva um novo usuário.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
	public ResponseEntity<Object> saveUsuario( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto UsuarioDto contendo os detalhes do novo usuário", required = true)@RequestBody @Valid UsuarioDto usuarioDto) {				
		var usuario = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(facade.saveUsuario(usuario));
	}
	
	@GetMapping
	@Operation(summary = "Listar todos os usuários", description = "Este endpoint retorna uma lista de todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<List<Usuario>> getTodosUsuarios(){
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllUsuario());
	}
	
	
	@GetMapping("/{id}")
    @Operation(summary = "Buscar um usuário pelo ID", description = "Este endpoint retorna um usuário com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> getUmUsuario(@Parameter(description = "ID do usuário", required = true) @PathVariable(value = "id")  UUID id){
		Optional<Usuario> usuarioOptional = facade.findUsuarioById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.get());
	}
	
	@DeleteMapping("/{id}")
    @Operation(summary = "Remover um usuário pelo ID", description = "Este endpoint remove um usuário com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> deleteUsuario( @Parameter(description = "ID do usuário a ser removido", required = true)
    @PathVariable(value = "id") UUID id){
		Optional<Usuario> usuarioOptional = facade.findUsuarioById(id);
		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
		facade.deleteUsuario(usuarioOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario removido com sucesso");
	}
	
	@PutMapping("/{id}")
    @Operation(summary = "Atualizar um usuário pelo ID", description = "Este endpoint atualiza um usuário existente com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
	public ResponseEntity<Object> updateUsuario( @Parameter(description = "ID do usuário a ser atualizado", required = true)
    @PathVariable(value = "id") UUID id,
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto UsuarioDto contendo os detalhes atualizados do usuário", required = true)
    @RequestBody @Valid UsuarioDto usuarioDto){
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
