package cl.duoc.fullstack.despacho.dto;

import java.time.LocalDateTime;

public record PedidoResponse(Long id, Long clienteId, Long productoId, Integer cantidad, Integer totalEstimado, String estado, LocalDateTime fechaCreacion) {}
