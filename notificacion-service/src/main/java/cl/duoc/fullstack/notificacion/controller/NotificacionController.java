package cl.duoc.fullstack.notificacion.controller;

import cl.duoc.fullstack.notificacion.dto.NotificacionRequest;
import cl.duoc.fullstack.notificacion.model.Notificacion;
import cl.duoc.fullstack.notificacion.service.NotificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@Tag(name = "Notificaciones", description = "API para el envío y registro de notificaciones (Email, SMS, etc.)")
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping("/enviar")
    @Operation(summary = "Enviar una notificación", description = "Registra y simula el envío de una nueva alerta al usuario.")
    @ApiResponse(responseCode = "201", description = "Notificación procesada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de envío inválidos")
    public ResponseEntity<Notificacion> enviarNotificacion(@Valid @RequestBody NotificacionRequest request) {
        return new ResponseEntity<>(notificacionService.enviarNotificacion(request), HttpStatus.CREATED);
    }

    @GetMapping("/historial/{destinatario}")
    @Operation(summary = "Obtener historial", description = "Obtiene todas las notificaciones enviadas a un destinatario específico (ej. correo).")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    public ResponseEntity<List<Notificacion>> obtenerHistorial(@PathVariable String destinatario) {
        return ResponseEntity.ok(notificacionService.obtenerHistorialPorDestinatario(destinatario));
    }
}