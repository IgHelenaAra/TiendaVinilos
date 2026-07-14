package cl.duoc.fullstack.envio.service;

import cl.duoc.fullstack.envio.dto.EnvioRequest;
import cl.duoc.fullstack.envio.model.Envio;
import cl.duoc.fullstack.envio.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @Test
    void crearEnvio_Exito() {
        EnvioRequest request = new EnvioRequest();
        request.setPedidoId(1L);
        request.setDireccion("Calle Falsa 123");
        request.setComuna("Santiago");
        request.setRegion("Metropolitana");

        when(envioRepository.findByPedidoId(1L)).thenReturn(Optional.empty());
        when(envioRepository.save(any(Envio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Envio resultado = envioService.crearEnvio(request);

        assertNotNull(resultado);
        assertEquals("PENDIENTE", resultado.getEstado());
        assertNotNull(resultado.getCodigoSeguimiento());
        assertTrue(resultado.getCodigoSeguimiento().startsWith("VIN-"));
        verify(envioRepository, times(1)).save(any(Envio.class));
    }

    @Test
    void crearEnvio_FallaPorPedidoDuplicado() {
        EnvioRequest request = new EnvioRequest();
        request.setPedidoId(1L);

        Envio envioExistente = new Envio();
        envioExistente.setPedidoId(1L);

        when(envioRepository.findByPedidoId(1L)).thenReturn(Optional.of(envioExistente));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            envioService.crearEnvio(request);
        });

        assertEquals("Ya existe un despacho registrado para el pedido ID: 1", exception.getMessage());
        verify(envioRepository, never()).save(any(Envio.class));
    }
}