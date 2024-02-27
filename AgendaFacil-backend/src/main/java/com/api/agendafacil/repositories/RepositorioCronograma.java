package com.api.agendafacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.models.UBS;

import java.util.List;
import java.util.UUID;

/**
* Interface de repositório para a entidade Cronograma.
* Esta interface fornece métodos de acesso aos dados relacionados ao Cronograma no banco de dados.
*/
@Repository
public interface RepositorioCronograma extends JpaRepository<Cronograma, UUID> {
	
	/**
     * Retorna uma lista de cronogramas associados a uma UBS específica.
     * @param ubs A UBS pela qual os cronogramas são filtrados.
     * @return Uma lista de cronogramas associados à UBS fornecida.
     */
	List<Cronograma> findByUbs(UBS ubs);
}

