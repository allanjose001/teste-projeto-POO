package com.api.agendafacil.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.agendafacil.facade.Facade;
import com.api.agendafacil.models.AuthRequest;
import com.api.agendafacil.models.Usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Controlador para autenticação de usuários.
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")

@Tag(name = "Autentição", description = "API para autenticar o usuário no sistema.")
public class AuthUsuarioController {
	
	@Autowired
	private Facade facade;

	@PostMapping("/login")
    @Operation(summary = "Autenticação de Usuário", description = "Esse endpoint Autentica um usuário com o nome de usuário e senha fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário autenticado com sucesso.", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
        @ApiResponse(responseCode = "400", description = "Autenticação falhou. Nome de usuário ou senha inválidos.")
    })
    public ResponseEntity<Object> autenticarUsuario( @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto AuthRequest contendo nome de usuário e senha.", required = true) AuthRequest Data) {
        String nome = Data.getNome();
        String senha = Data.getSenha();
        
        Usuario usuarioAutenticado = facade.autenticarUsuario(nome, senha);
        
        if (usuarioAutenticado != null) {
        	// Autenticação bem-sucedida, retornar um token de autenticação ou qualquer outra informação necessária
        	return ResponseEntity.status(HttpStatus.OK).body("Autenticado com sucesso.");
        } else {
            // Autenticação falhou
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticação falhou. Nome de usuário ou senha inválidos.");
        }
    }
}
