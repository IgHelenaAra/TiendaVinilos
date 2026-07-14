package cl.duoc.fullstack.resena.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI resenaServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Reseñas - Tienda de Vinilos")
                        .description("Microservicio para gestionar reseñas y calificaciones.")
                        .version("1.0.0"));
    }
}