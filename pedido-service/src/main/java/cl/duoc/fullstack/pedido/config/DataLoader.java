package cl.duoc.fullstack.pedido.config;

import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.model.PedidoItem;
import cl.duoc.fullstack.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (pedidoRepository.count() == 0) {
            Faker faker = new Faker();

            Pedido pedidoDemo = Pedido.builder()
                    .rutCliente("11.111.111-1")
                    .estado("PENDIENTE")
                    .total(25000)
                    .items(new ArrayList<>())
                    .build();

            PedidoItem item = PedidoItem.builder()
                    .pedido(pedidoDemo)
                    .skuVinilo("VIN-" + faker.commerce().promotionCode().toUpperCase())
                    .cantidad(1)
                    .precioUnitario(25000)
                    .build();

            pedidoDemo.getItems().add(item);
            pedidoRepository.save(pedidoDemo);

            System.out.println("✅ Datos iniciales de Pedidos cargados con éxito usando Datafaker.");
        }
    }
}