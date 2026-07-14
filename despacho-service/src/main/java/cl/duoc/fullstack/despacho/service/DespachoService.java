package cl.duoc.fullstack.despacho.service;

import cl.duoc.fullstack.despacho.dto.*;
import cl.duoc.fullstack.despacho.exception.ResourceNotFoundException;
import cl.duoc.fullstack.despacho.model.Despacho;
import cl.duoc.fullstack.despacho.repository.DespachoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DespachoService {
    private final DespachoRepository repository;
    private final WebClient.Builder webClientBuilder;

    @Value("${services.pedido.url:http://PEDIDO-SERVICE}")
    private String pedidoServiceUrl;

    public List<Despacho> listar() { return repository.findAll(); }
    public Despacho buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe despacho con ID " + id)); }

    public Despacho crear(DespachoRequest request) {
        PedidoResponse pedido = webClientBuilder.build().get()
                .uri(pedidoServiceUrl + "/api/pedidos/{id}", request.getPedidoId())
                .retrieve().bodyToMono(PedidoResponse.class).block();
        Despacho despacho = Despacho.builder()
                .pedidoId(pedido.id())
                .direccion(request.getDireccion())
                .comuna(request.getComuna())
                .estado("PROGRAMADO")
                .fechaProgramada(LocalDate.now().plusDays(2))
                .build();
        log.info("Despacho programado para pedido {}", pedido.id());
        return repository.save(despacho);
    }

    public void eliminar(Long id) { repository.delete(buscarPorId(id)); }
}
