package com.s1.LogiTrack.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestion De Bodegas --LogiTrack")
                        .version("1.0")
                        .description("Tablas generales para el uso y manipulacion de la informacion de las bodegas, \n" +
                                "no se puede usar el pruebalo por seguridad usar el login para hacer uso y pruebas de los datos almacenados")
                );
    }
}
