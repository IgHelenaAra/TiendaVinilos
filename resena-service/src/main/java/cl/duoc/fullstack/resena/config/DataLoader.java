package cl.duoc.fullstack.resena.config;

import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ResenaRepository resenaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (resenaRepository.count() == 0) {
            resenaRepository.save(Resena.builder()
                    .productoId("VIN-DARK-SIDE")
                    .calificacion(5)
                    .comentario("¡Excelente disco, sonido impecable!")
                    .build());
            System.out.println("✅ Datos iniciales de Reseñas cargados con éxito.");
        }
    }
}