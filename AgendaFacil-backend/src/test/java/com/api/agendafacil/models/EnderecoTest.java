package com.api.agendafacil.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.agendafacil.repositories.RepositorioEndereco;

import jakarta.persistence.EntityManager;


@DataJpaTest
@ActiveProfiles("test")
class EnderecoTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	RepositorioEndereco repositorioEndereco;
	
	private Endereco endereco;
	
	@BeforeEach
	void criarEndereco() {
		Endereco endereco1 = new Endereco("Rua Teste", "Bairro Teste", "Cidade Teste", "Estado Teste");
		this.entityManager.persist(endereco1);
		this.entityManager.flush();
		
		this.endereco = endereco1;
		//manda para dentro do repositorio
	}
	
	@Test
    @DisplayName("Retorna endereco do banco de dados")
    void test() {
        // Recupera o endereco do banco de dados
        Endereco enderecoDoBanco = repositorioEndereco.findById(endereco.getId()).orElse(null);

        // Realizar as verificações usando os métodos de assert do JUnit
        assertNotNull(enderecoDoBanco);
        assertEquals(endereco.getRua(), enderecoDoBanco.getRua());
        assertEquals(endereco.getBairro(), enderecoDoBanco.getBairro());
        assertEquals(endereco.getCidade(), enderecoDoBanco.getCidade());
        assertEquals(endereco.getEstado(), enderecoDoBanco.getEstado());
    }
}
