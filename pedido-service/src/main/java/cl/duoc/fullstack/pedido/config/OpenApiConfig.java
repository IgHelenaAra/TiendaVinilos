package cl.duoc.fullstack.pedido.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI pedidoServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pedidos - Tienda de Vinilos")
                        .description("Microservicio para la gestión y creación de órdenes de compra.")
                        .version("1.0.0"));
    }
}