package cl.duoc.fullstack.envio.service;

import cl.duoc.fullstack.envio.dto.EnvioRequest;
import cl.duoc.fullstack.envio.model.Envio;
import cl.duoc.fullstack.envio.repository.EnvioRepository;
import cl.duoc.fullstack.envio.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;

    @Transactional(readOnly = true)
    public List<Envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Envio obtenerPorCodigoSeguimiento(String codigo) {
        return envioRepository.findByCodigoSeguimiento(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con el código: " + codigo));
    }

    @Transactional
    public Envio crearEnvio(EnvioRequest request) {
        if (envioRepository.findByPedidoId(request.getPedidoId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un despacho registrado para el pedido ID: " + request.getPedidoId());
        }

        String codigoGenerado = "VIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Envio nuevoEnvio = Envio.builder()
                .pedidoId(request.getPedidoId())
                .direccion(request.getDireccion())
                .comuna(request.getComuna())
                .region(request.getRegion())
                .estado("PENDIENTE")
                .codigoSeguimiento(codigoGenerado)
                .build();

        return envioRepository.save(nuevoEnvio);
    }

    @Transactional
    public Envio actualizarEstado(Long id, String nuevoEstado) {
        Envio envio = envioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Envío no encontrado con ID: " + id));
        envio.setEstado(nuevoEstado);
        return envioRepository.save(envio);
    }
}