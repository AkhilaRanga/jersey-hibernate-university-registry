package com.learn.javaweb.config;

import org.glassfish.jersey.server.ResourceConfig;

import java.util.Set;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.integration.SwaggerConfiguration;

public class AppConfig extends ResourceConfig {

    public AppConfig() {
        packages("com.learn.javaweb.resource");

        configureSwagger();
    }

    private void configureSwagger() {
        SwaggerConfiguration swaggerConfig = new SwaggerConfiguration()
                .prettyPrint(true)
                .resourcePackages(Set.of("com.learn.javaweb.resource"))
                .openAPI(new io.swagger.v3.oas.models.OpenAPI()
                    .info(new io.swagger.v3.oas.models.info.Info()
                        .title("University registry")
                        .version("1.0.0")
                        .description("Swagger API for Jersey + Hibernate java application")));

        try {
            OpenApiResource openApiResource = new OpenApiResource();
            openApiResource.setOpenApiConfiguration(swaggerConfig);
            register(openApiResource);
        } catch (Exception e) {
            throw new RuntimeException("Failed to configure OpenAPI", e);
        }
    }
}
