package com.github.name23523535.smartstorage.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI smartWarehouseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Smart Warehouse API")
                        .description("API для управления складом")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Документация проекта")
                        .url("https://github.com/твоя-репа/SmartWarehouse"));
    }
}
