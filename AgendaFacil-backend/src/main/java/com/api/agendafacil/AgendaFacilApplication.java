package com.api.agendafacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AgendaFacilApplication {

    public static void main(String[] args) {
    	// servira para nos ajudar a instanciar objetos e manipular eles;
        SpringApplication.run(AgendaFacilApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Seila";
    }
}
