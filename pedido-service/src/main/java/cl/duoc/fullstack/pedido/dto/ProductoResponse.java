package cl.duoc.fullstack.pedido.dto;

public record ProductoResponse(Long id, String nombre, String categoria, Integer stock, Integer precio) {}
