package com.github.benimarushiimon.webservise.configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConf {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot REST API")
                        .description("Document CRUD operations using Spring Boot with PostgreSQL and Liquibase migration.")
                        .version("1.0.0")
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache License Version 2.0")));
    }
}
