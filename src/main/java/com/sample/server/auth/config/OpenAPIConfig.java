package com.sample.server.auth.config;

import java.util.List;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    @Value("${sample.openapi.dev-url}")
    private String devUrl;
    @Value("${sample.openapi.prod-url}")
    private String prodUrl;
    @Bean
    public OpenAPI myOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");
        openAPI.setServers(List.of(devServer, prodServer));

        Contact contact = new Contact();
        contact.setEmail("sample@gmail.com");
        contact.setName("Sample Koder");
        contact.setUrl("https://www.sample.com");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");
        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://www.sample.com/terms")
                .license(mitLicense);
        openAPI.setInfo(info);
        openAPI.components(new Components()
                        .addSecuritySchemes("JWTToken", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                        )
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList("JWTToken")
                );
        return openAPI;
    }
}
