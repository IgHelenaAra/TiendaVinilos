package cl.duoc.fullstack.pago.service;

import cl.duoc.fullstack.pago.dto.*;
import cl.duoc.fullstack.pago.exception.ResourceNotFoundException;
import cl.duoc.fullstack.pago.model.Pago;
import cl.duoc.fullstack.pago.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository repository;
    private final WebClient.Builder webClientBuilder;

    @Value("${services.pedido.url:http://PEDIDO-SERVICE}")
    private String pedidoServiceUrl;

    public List<Pago> listar() { return repository.findAll(); }
    public Pago buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe pago con ID " + id)); }

    public Pago crear(PagoRequest request) {
        PedidoResponse pedido = webClientBuilder.build().get()
                .uri(pedidoServiceUrl + "/api/pedidos/{id}", request.getPedidoId())
                .retrieve().bodyToMono(PedidoResponse.class).block();
        Pago pago = Pago.builder()
                .pedidoId(pedido.id())
                .metodo(request.getMetodo())
                .monto(request.getMonto())
                .estado("APROBADO")
                .fechaPago(LocalDateTime.now())
                .build();
        log.info("Pago creado para pedido {}", pedido.id());
        return repository.save(pago);
    }

    public void eliminar(Long id) { repository.delete(buscarPorId(id)); }
}
