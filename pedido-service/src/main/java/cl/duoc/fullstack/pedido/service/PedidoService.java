package cl.duoc.fullstack.pedido.service;

import cl.duoc.fullstack.pedido.dto.PedidoRequest;
import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.model.PedidoItem;
import cl.duoc.fullstack.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public Pedido crearPedido(PedidoRequest request) {
        try {
            webClientBuilder.build().get()
                    .uri("http://cliente-service/api/clientes/rut/" + request.getRutCliente())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error: El cliente con RUT " + request.getRutCliente() + " no está registrado en el sistema.");
        }
        Pedido nuevoPedido = Pedido.builder()
                .rutCliente(request.getRutCliente())
                .estado("PENDIENTE")
                .items(new ArrayList<>())
                .build();

        int total = 0;
        for (var itemReq : request.getItems()) {
            PedidoItem item = PedidoItem.builder()
                    .pedido(nuevoPedido)
                    .skuVinilo(itemReq.getSkuVinilo())
                    .cantidad(itemReq.getCantidad())
                    .precioUnitario(itemReq.getPrecioUnitario())
                    .build();
            nuevoPedido.getItems().add(item);
            total += (itemReq.getCantidad() * itemReq.getPrecioUnitario());
        }

        nuevoPedido.setTotal(total);
        return pedidoRepository.save(nuevoPedido);
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerPedidosPorCliente(String rutCliente) {
        return pedidoRepository.findByRutCliente(rutCliente);
    }

    @Transactional(readOnly = true)
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }
}
