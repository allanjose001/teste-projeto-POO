package com.api.agendafacil.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.agendafacil.repositories.RepositorioUsuario;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class UsuarioTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
    RepositorioUsuario repositorioUsuario;

    private Usuario usuario;

    @BeforeEach
    void criarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setDataDeNascimento(LocalDate.of(1990, 1, 1));
        usuario.setCpf("1234568");
        usuario.setSus("9418232");
        usuario.setTelefone("987654321");
        usuario.setEmail("teste@gmail.com");
        usuario.setSenha("123");

        this.entityManager.persist(usuario);
        this.entityManager.flush();

        this.usuario = usuario;
    }

    @Test
    @DisplayName("Retorna Usuario do banco de dados")
    void test() {
        // Recupera o usuario do banco de dados
        Usuario usuarioDoBanco = repositorioUsuario.findById(usuario.getId()).orElse(null);

        // Realiza as verificações usando os métodos de assert do JUnit
        assertNotNull(usuarioDoBanco);
        assertEquals(usuario.getNome(), usuarioDoBanco.getNome());
        assertEquals(usuario.getDataDeNascimento(), usuarioDoBanco.getDataDeNascimento());
        assertEquals(usuario.getCpf(), usuarioDoBanco.getCpf());
        assertEquals(usuario.getSus(), usuarioDoBanco.getSus());
        assertEquals(usuario.getTelefone(), usuarioDoBanco.getTelefone());
        assertEquals(usuario.getEmail(), usuarioDoBanco.getEmail());
        assertEquals(usuario.getSenha(), usuarioDoBanco.getSenha());
    }

}
