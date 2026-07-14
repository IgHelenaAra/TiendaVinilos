package cl.duoc.fullstack.notificacion.config;

import cl.duoc.fullstack.notificacion.model.Notificacion;
import cl.duoc.fullstack.notificacion.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final NotificacionRepository notificacionRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Verificando si existen datos iniciales en notificacion-service...");

        if (notificacionRepository.count() == 0) {
            log.info("Cargando datos de prueba para Notificaciones...");

            notificacionRepository.save(Notificacion.builder()
                    .destinatario("cliente1@duocuc.cl")
                    .asunto("Bienvenido a la plataforma")
                    .mensaje("Gracias por registrarte en nuestra plataforma de pedidos.")
                    .tipo("EMAIL")
                    .estado("ENVIADA")
                    .build());

            notificacionRepository.save(Notificacion.builder()
                    .destinatario("+56912345678")
                    .mensaje("Tu pedido #1024 ha sido confirmado y está en preparación.")
                    .tipo("SMS")
                    .estado("ENVIADA")
                    .build());

            log.info("Datos de prueba cargados exitosamente.");
        }
    }
}