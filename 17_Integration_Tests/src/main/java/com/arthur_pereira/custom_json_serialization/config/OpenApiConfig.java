package com.arthur_pereira.custom_json_serialization.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().
                title("Art's REST API's RESTful").
                version("v1").
                description("My first swagger documentation").
                license(new License().name("No license").url("https://choosealicense.com/")));
    }
}
