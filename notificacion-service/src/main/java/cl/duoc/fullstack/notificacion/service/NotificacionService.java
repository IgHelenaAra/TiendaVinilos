package cl.duoc.fullstack.notificacion.service;

import cl.duoc.fullstack.notificacion.dto.NotificacionRequest;
import cl.duoc.fullstack.notificacion.model.Notificacion;
import cl.duoc.fullstack.notificacion.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public Notificacion enviarNotificacion(NotificacionRequest request) {
        log.info("Simulando el envío de notificación tipo {} a {}", request.getTipo(), request.getDestinatario());

        Notificacion notificacion = Notificacion.builder()
                .destinatario(request.getDestinatario())
                .asunto(request.getAsunto())
                .mensaje(request.getMensaje())
                .tipo(request.getTipo().toUpperCase())
                .estado("ENVIADA") // Asumimos éxito para la simulación
                .build();

        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> obtenerHistorialPorDestinatario(String destinatario) {
        return notificacionRepository.findByDestinatario(destinatario);
    }
}