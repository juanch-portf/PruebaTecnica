package com.jacg.Prueba.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger{
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Management API")
                        .version("1.0.0")
                        .description("REST API para gestión de usuarios - Prueba Técnica Java Developer")
                        .contact(new Contact()
                                .name("Ángel Chimal")
                                .email("juanangelchimalgomez@gmail.com")))
                .servers(List.of(
                        new Server().url("http://localhost:9001").description("Servidor Local")
                ));
    }
}