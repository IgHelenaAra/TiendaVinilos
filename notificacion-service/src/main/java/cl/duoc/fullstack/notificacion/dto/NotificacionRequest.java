package cl.duoc.fullstack.notificacion.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificacionRequest {

    @NotBlank(message = "El destinatario es obligatorio")
    private String destinatario;

    private String asunto;

    @NotBlank(message = "El mensaje no puede estar vacío")
    private String mensaje;

    @NotBlank(message = "El tipo de notificación es obligatorio (ej. EMAIL, SMS)")
    private String tipo;
}