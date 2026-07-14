package cl.duoc.fullstack.envio.config;

import cl.duoc.fullstack.envio.model.Envio;
import cl.duoc.fullstack.envio.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EnvioRepository envioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (envioRepository.count() == 0) {
            envioRepository.save(Envio.builder()
                    .pedidoId(999L)
                    .direccion("Av. Vicuña Mackenna 7110")
                    .comuna("La Florida")
                    .region("Metropolitana")
                    .estado("EN_TRANSITO")
                    .codigoSeguimiento("VIN-DEMO1234")
                    .build());

            System.out.println("✅ Datos iniciales de Envío cargados con éxito.");
        }
    }
}