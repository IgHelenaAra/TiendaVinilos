package cl.duoc.fullstack.inventario.config;

import cl.duoc.fullstack.inventario.model.Inventario;
import cl.duoc.fullstack.inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (inventarioRepository.count() == 0) {
            inventarioRepository.save(Inventario.builder()
                    .skuVinilo("VIN-DARK-SIDE")
                    .cantidadDisponible(50)
                    .ubicacionBodega("Bodega Central - Pasillo A")
                    .build());
            System.out.println("✅ Datos iniciales de Inventario cargados con éxito.");
        }
    }
}