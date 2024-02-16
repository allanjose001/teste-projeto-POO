package com.api.agendafacil.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Usuario;
import com.api.agendafacil.models.UsuarioLogin;
import com.api.agendafacil.repositories.RepositorioUsuario;
import com.api.agendafacil.repositories.RepositorioUsuarioLogin;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	@Autowired
	private RepositorioUsuarioLogin repositorioUsuarioLogin;

	public Usuario autenticarUsuario(String nome, String senha) {
	 // Realiza a autenticação do usuário com base no nome de usuário e senha
        UsuarioLogin usuarioLogin = repositorioUsuarioLogin.findByNome(nome);
        if (usuarioLogin != null && usuarioLogin.getSenha().equals(senha)) {
            return usuarioLogin.getUsuario();
        } else {
            return null; 
        }
	    
    }
	

    @Transactional
    public Usuario criarUsuarioComCredenciais(Usuario usuario, String nome, String senha) {
        // Verifica se o nome de usuário já existe
        UsuarioLogin usuarioExistente = repositorioUsuarioLogin.findByNome(nome);
        if (usuarioExistente != null) {
            throw new IllegalArgumentException("O nome de usuário já está em uso.");
        }

        // Cria as credenciais de login
        UsuarioLogin usuarioLogin = new UsuarioLogin(nome, senha, usuario);

        // Salva as credenciais de login
        repositorioUsuarioLogin.save(usuarioLogin);

        // Define as credenciais de login para o usuário
        usuario.setUsuarioLogin(usuarioLogin);

        // Salva o usuário
        return repositorioUsuario.save(usuario);
    }
	 
	@Transactional
	public Usuario save(Usuario usuario) {
		try {
			// Tenta salvar o usuário no banco de dados
			return repositorioUsuario.save(usuario);
	    } catch (DataIntegrityViolationException ex) {
	        // Captura exceção de violação de integridade (por exemplo, CPF duplicado)
	        // Lança uma exceção personalizada informando sobre o erro de integridade
	    	throw new IllegalArgumentException("Erro ao salvar o usuário. Verifique os dados fornecidos.", ex);
	     }
	}
	//esse metodo retorna uma lista com todos os ussuarios que estão contidos no meu banco de dados
	public List<Usuario> findAll() {
		return repositorioUsuario.findAll();
	}
	
	//metodo de busca usuario por id 
	public Optional<Usuario> findById(UUID id) {
		return repositorioUsuario.findById(id);
	}

	 @Transactional
	 public void delete(Usuario usuario) {
		 // Verifica se o usuário existe antes de tentar excluí-lo
		 if (repositorioUsuario.existsById(usuario.getId())) {
	     // Exclui o usuário se existir
			 repositorioUsuario.delete(usuario);
	     } else {
	     // Lança uma exceção se o usuário não existir para exclusão
	    	 throw new IllegalArgumentException("Usuário não encontrado para exclusão.");
	     }
	 }
}