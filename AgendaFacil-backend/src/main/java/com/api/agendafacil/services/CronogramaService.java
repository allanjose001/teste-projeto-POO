package com.api.agendafacil.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.repositories.RepositorioCronograma;
import java.util.List;

@Service
public class CronogramaService {

    @Autowired
    private RepositorioCronograma cronogramaRepository;

    public void adicionarEvento(Cronograma cronograma) {
        cronogramaRepository.save(cronograma);
    }

    public List<Cronograma> listarEventos() {
        return cronogramaRepository.findAll();
    }
    
    //adicionar delete e editar
}
