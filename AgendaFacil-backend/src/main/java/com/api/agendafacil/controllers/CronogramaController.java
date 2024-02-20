package com.api.agendafacil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.services.CronogramaService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cronograma")
public class CronogramaController {
	@Autowired
    private CronogramaService cronogramaService;

    @PostMapping("/adicionarEvento")
    public ResponseEntity<?> adicionarEvento(@RequestBody Cronograma cronograma) {
        cronogramaService.adicionarEvento(cronograma);
        return new ResponseEntity<>("Evento adicionado ao cronograma com sucesso", HttpStatus.CREATED);
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<Cronograma>> listarEventos() {
        List<Cronograma> eventos = cronogramaService.listarEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    // atualizar um evento existente
    @PutMapping("/atualizarEvento/{id}")
    public ResponseEntity<?> atualizarEvento(@PathVariable UUID id, @RequestBody Cronograma cronograma) {
        cronogramaService.atualizarEvento(id, cronograma);
        return new ResponseEntity<>("Evento atualizado com sucesso", HttpStatus.OK);
    }
}