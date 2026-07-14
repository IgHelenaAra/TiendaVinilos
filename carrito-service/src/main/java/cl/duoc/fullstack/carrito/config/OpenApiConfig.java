package cl.duoc.fullstack.carrito.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI carritoServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Carrito - Tienda de Vinilos")
                        .description("Microservicio para gestionar carritos de compras de los clientes.")
                        .version("1.0.0"));
    }
}