package cl.duoc.fullstack.carrito.config;

import cl.duoc.fullstack.carrito.model.Carrito;
import cl.duoc.fullstack.carrito.model.CarritoItem;
import cl.duoc.fullstack.carrito.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CarritoRepository carritoRepository;
    @Override
    public void run(String... args) throws Exception {
        if (carritoRepository.count() == 0) {
            Carrito carritoDemo = Carrito.builder()
                    .usuarioId(1L)
                    .estado("ACTIVO")
                    .total(25000)
                    .items(new ArrayList<>())
                    .build();
            CarritoItem itemDemo = CarritoItem.builder()
                    .carrito(carritoDemo)
                    .skuVinilo("VIN-DARK-SIDE")
                    .cantidad(1)
                    .precioUnitario(25000)
                    .build();
            carritoDemo.getItems().add(itemDemo);
            carritoRepository.save(carritoDemo);
            System.out.println("✅ Datos iniciales de Carrito cargados con éxito.");
        }
    }
}