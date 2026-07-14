package cl.duoc.fullstack.carrito.service;
import cl.duoc.fullstack.carrito.dto.AgregarItemRequest;
import cl.duoc.fullstack.carrito.exception.ResourceNotFoundException;
import cl.duoc.fullstack.carrito.model.Carrito;
import cl.duoc.fullstack.carrito.model.CarritoItem;
import cl.duoc.fullstack.carrito.repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final WebClient.Builder webClientBuilder;

    @Transactional
    public Carrito agregarItem(AgregarItemRequest request) {
        try {
            webClientBuilder.build().get()
                    .uri("http://inventario-service/api/inventario/" + request.getSkuVinilo())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (Exception e) {
            throw new IllegalArgumentException("No se pudo verificar el stock o el vinilo no existe en el inventario.");
        }

        Carrito carrito = carritoRepository.findByUsuarioIdAndEstado(request.getUsuarioId(), "ACTIVO")
                .orElse(Carrito.builder()
                        .usuarioId(request.getUsuarioId())
                        .estado("ACTIVO")
                        .total(0)
                        .items(new ArrayList<>())
                        .build());

        Optional<CarritoItem> itemExistente = carrito.getItems().stream()
                .filter(i -> i.getSkuVinilo().equals(request.getSkuVinilo()))
                .findFirst();

        if (itemExistente.isPresent()) {
            CarritoItem item = itemExistente.get();
            item.setCantidad(item.getCantidad() + request.getCantidad());
        } else {
            CarritoItem nuevoItem = CarritoItem.builder()
                    .carrito(carrito)
                    .skuVinilo(request.getSkuVinilo())
                    .cantidad(request.getCantidad())
                    .precioUnitario(request.getPrecioUnitario())
                    .build();
            carrito.getItems().add(nuevoItem);
        }

        recalcularTotal(carrito);
        return carritoRepository.save(carrito);
    }

    @Transactional(readOnly = true)
    public Carrito obtenerCarritoActivo(Long usuarioId) {
        return carritoRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO")
                .orElseThrow(() -> new ResourceNotFoundException("No hay carrito activo para el usuario: " + usuarioId));
    }

    private void recalcularTotal(Carrito carrito) {
        int total = carrito.getItems().stream()
                .mapToInt(item -> item.getCantidad() * item.getPrecioUnitario())
                .sum();
        carrito.setTotal(total);
    }
}