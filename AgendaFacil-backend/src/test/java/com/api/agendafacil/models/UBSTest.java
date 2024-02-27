package com.api.agendafacil.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.agendafacil.repositories.RepositorioUBS;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class UBSTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	RepositorioUBS repositorioUBS;
	
	private UBS ubs;
	
	private Endereco criarEndereco() {
		Endereco endereco = new Endereco("Rua Teste", "Bairro Teste", "Cidade Teste", "Estado Teste");
		this.entityManager.persist(endereco);
		this.entityManager.flush();
		return endereco;
		//cria um endereco e o retorna
	}
	
	private UBS criarUBS(Endereco endereco) {
		UBS ubs = new UBS("resources/foto", "Nome Teste", endereco);
		this.entityManager.persist(ubs);
		this.entityManager.flush();
		return ubs;
		//cria uma UBS e usa um endereco criado anteriormente
	}
	
	@BeforeEach
	void inicializar() {
		Endereco endereco1 = criarEndereco();
		UBS ubs = criarUBS(endereco1);
		
		this.ubs = ubs;
	}
	
	@Test
	@DisplayName("retorna ubs do banco de dados")
	void test() {
		UBS ubsDoBanco = repositorioUBS.findById(ubs.getId()).orElse(null);
	
		assertNotNull(ubsDoBanco);
		assertEquals(ubs.getImagemUBS(), ubsDoBanco.getImagemUBS());
        assertEquals(ubs.getNomeUBS(), ubsDoBanco.getNomeUBS());

        // Verifica a associação com o Endereco
        assertNotNull(ubsDoBanco.getEndereco());
        assertEquals(ubs.getEndereco().getRua(), ubsDoBanco.getEndereco().getRua());
        assertEquals(ubs.getEndereco().getBairro(), ubsDoBanco.getEndereco().getBairro());
        assertEquals(ubs.getEndereco().getCidade(), ubsDoBanco.getEndereco().getCidade());
        assertEquals(ubs.getEndereco().getEstado(), ubsDoBanco.getEndereco().getEstado());
		
	}
	
}