package cl.duoc.fullstack.inventario.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI inventarioServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Inventario - Tienda de Vinilos")
                        .description("Microservicio para el control de stock en bodega.")
                        .version("1.0.0"));
    }
}