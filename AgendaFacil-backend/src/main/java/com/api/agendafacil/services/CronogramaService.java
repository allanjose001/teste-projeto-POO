package com.api.agendafacil.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.models.UBS;
import com.api.agendafacil.repositories.RepositorioCronograma;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CronogramaService implements CronogramaServiceInterface{

    @Autowired
    private RepositorioCronograma cronogramaRepository;
    
    //com essa anotação configuramos a nossa aplicação apenas para o admin ter acesso ao metodo
  	//@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Cronograma adicionarCronograma(Cronograma cronograma)throws IllegalArgumentException{
    	if (cronograma.getTiposConsulta() == null || cronograma.getVagas() == null) {
            throw new IllegalArgumentException("O tipo de consulta e a quantidade de vagas são obrigatórios.");
        }
        return cronogramaRepository.save(cronograma);
    }
  	//metodo de usuario ver a lista
  	
    public List<Cronograma> listarCronograma() {
        return cronogramaRepository.findAll();
    }
	public Optional<Cronograma> listarCronogramaPorId(UUID id) {
		return cronogramaRepository.findById(id);
	}
    
    public List<Cronograma> listarCronogramaPorUBS(UBS ubs) {
        return cronogramaRepository.findByUbs(ubs);
    }
    
    //vamos procurar o id e caso ele seja encontrado no nosso cronograma vamos atualizar ele, comm s novos ddados
    //com essa anotação configuramos a nossa aplicação apenas para o admin ter acesso ao metodo
  	//@PreAuthorize("hasRole('ROLE_ADMIN')")
    public Cronograma atualizarCronograma(UUID id, Cronograma novoCronograma) {
        Cronograma cronogramaExistente = cronogramaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado com o id: " + id));

        // Atualize os campos relevantes do cronograma existente com os valores do novo cronograma
        cronogramaExistente.setDiasSemana(novoCronograma.getDiasSemana());
        cronogramaExistente.setTiposConsulta(novoCronograma.getTiposConsulta());
        cronogramaExistente.setVagas(novoCronograma.getVagas());
        
        return cronogramaRepository.save(cronogramaExistente);
    }
  	
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	public void deleteCronograma(Cronograma cronograma) {
		cronogramaRepository.delete(cronograma);
	}
  	
  	
}