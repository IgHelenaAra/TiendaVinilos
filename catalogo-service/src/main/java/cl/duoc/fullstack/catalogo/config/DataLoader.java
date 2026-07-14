package cl.duoc.fullstack.catalogo.config;

import cl.duoc.fullstack.catalogo.model.Destacado;
import cl.duoc.fullstack.catalogo.repository.DestacadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final DestacadoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(Destacado.builder().skuVinilo("VIN-DARK-SIDE").posicion(1).activo(true).build());
            System.out.println("✅ Datos iniciales de Catálogo cargados con éxito.");
        }
    }
}