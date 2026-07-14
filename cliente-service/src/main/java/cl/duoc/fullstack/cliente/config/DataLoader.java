package cl.duoc.fullstack.cliente.config;

import cl.duoc.fullstack.cliente.model.Cliente;
import cl.duoc.fullstack.cliente.repository.ClienteRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner cargarDatos(ClienteRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Cliente.builder()
                        .rut("11.111.111-1")
                        .nombre("Ana Torres")
                        .email("ana.torres@example.com")
                        .telefono("+56 9 1111 1111")
                        .build());

                Faker faker = new Faker();
                for (int i = 0; i < 4; i++) {
                    repository.save(Cliente.builder()
                            .rut(faker.idNumber().valid())
                            .nombre(faker.name().fullName())
                            .email(faker.internet().emailAddress())
                            .telefono(faker.phoneNumber().cellPhone())
                            .build());
                }
            }
        };
    }
}
