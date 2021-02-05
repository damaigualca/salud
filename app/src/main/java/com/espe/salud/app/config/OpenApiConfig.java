package com.espe.salud.app.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {




    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("salud")
                        .description("Proyecto de estructura de Salud Espe")
                        .version("1.0.0")
                        .termsOfService("apiTermsOfServiceUrl")
                        .license(new License().name("salud").url("https://www.saludespe.edu.ec")));
    }
}
