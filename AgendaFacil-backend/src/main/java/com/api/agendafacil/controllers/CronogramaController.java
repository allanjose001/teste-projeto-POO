package com.api.agendafacil.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.agendafacil.models.Cronograma;
import com.api.agendafacil.services.CronogramaService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
}