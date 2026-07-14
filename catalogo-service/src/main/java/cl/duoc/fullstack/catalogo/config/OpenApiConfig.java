package cl.duoc.fullstack.catalogo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI catalogoServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Catálogo - Tienda de Vinilos")
                        .description("Microservicio agregador que expone el catálogo y consume datos de otros servicios.")
                        .version("1.0.0"));
    }
}