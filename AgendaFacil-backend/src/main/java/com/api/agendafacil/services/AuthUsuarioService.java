package com.api.agendafacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Usuario;
import com.api.agendafacil.repositories.RepositorioUsuario;

@Service
public class AuthUsuarioService {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	public Usuario autenticarUsuario(String nome, String senha) {
	 //Realiza a autenticação do usuário com base no nome de usuário e senha
        Usuario usuarioLogin = repositorioUsuario.findByNome(nome);
       //System.out.println("retorno do findbyid: " + usuarioLogin.getNome());
        
        if (usuarioLogin != null && usuarioLogin.getSenha().equals(senha)) {
            return usuarioLogin;
        } else {
            return null; 
        }
    }

}
