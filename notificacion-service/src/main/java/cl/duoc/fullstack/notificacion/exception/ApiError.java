package cl.duoc.fullstack.producto.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime fecha,
        int estado,
        String error,
        List<String> mensajes,
        String ruta
) {}
