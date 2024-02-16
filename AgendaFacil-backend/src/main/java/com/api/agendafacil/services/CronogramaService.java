package com.api.agendafacil.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.repositories.RepositorioCronograma;
import java.util.List;
import java.util.UUID;

@Service
public class CronogramaService {

    @Autowired
    private RepositorioCronograma cronogramaRepository;
    
  //com essa anotação configuramos a nossa aplicação apenas para o admin ter acesso ao metodo
  	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adicionarEvento(Cronograma cronograma) {
        cronogramaRepository.save(cronograma);
    }

    public List<Cronograma> listarEventos() {
        return cronogramaRepository.findAll();
    }
    
    //vamos procurar o id e caso ele seja encontrado no nosso cronograma vamos atualizar ele, comm s novos ddados
  //com essa anotação configuramos a nossa aplicação apenas para o admin ter acesso ao metodo
  	@PreAuthorize("hasRole('ROLE_ADMIN')")
    public void atualizarEvento(UUID id, Cronograma novoCronograma) {
        Cronograma cronogramaExistente = cronogramaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado com o id: " + id));

        // Atualize os campos relevantes do cronograma existente com os valores do novo cronograma
        cronogramaExistente.setTipoConsulta(novoCronograma.getTipoConsulta());
        cronogramaExistente.setDataHora(novoCronograma.getDataHora());
        // Atualize outros campos conforme necessário

        // Salve o cronograma atualizado
        cronogramaRepository.save(cronogramaExistente);
    }
}
