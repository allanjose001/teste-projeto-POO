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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")

public class AuthUsuarioController {
	
	@Autowired
	private Facade facade;
	
	@PostMapping("/login")
    public ResponseEntity<Object> autenticarUsuario(@RequestBody AuthRequest Data) {
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
