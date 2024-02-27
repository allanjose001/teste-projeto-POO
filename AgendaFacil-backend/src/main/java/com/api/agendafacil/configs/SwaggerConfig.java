package com.api.agendafacil.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
    	return new OpenAPI()
                .info(new Info()
                    .title("Agenda Facil")
                    .version("1.0.0")
                    .description("API para agendamento de consultas."
                    		+ " Permite aos usuários agendar consultas, "
                    		+ "cancelar consultas existentes e visualizar consultas"
                    		+ " agendadas. Esta API aceita e retorna dados no"
                    		+ " formato JSON.")
                    .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("Alcielma Luzinte da Silva \n Allan José \n Pedro")
                        .email("equipe@exemplo.com")
                        .url("https://exemplo.com/equipe")));
    }
}
