package cl.duoc.fullstack.carrito.service;
import cl.duoc.fullstack.carrito.model.Carrito;
import cl.duoc.fullstack.carrito.repository.CarritoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarritoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private CarritoService carritoService;

    @Test
    void obtenerCarritoActivo_FallaSiNoExiste() {
        when(carritoRepository.findByUsuarioIdAndEstado(1L, "ACTIVO")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> carritoService.obtenerCarritoActivo(1L));
        verify(carritoRepository, times(1)).findByUsuarioIdAndEstado(1L, "ACTIVO");
    }

    @Test
    void obtenerCarritoActivo_Exito() {
        Carrito carrito = new Carrito();
        carrito.setId(1L);
        carrito.setUsuarioId(1L);
        carrito.setEstado("ACTIVO");

        when(carritoRepository.findByUsuarioIdAndEstado(1L, "ACTIVO")).thenReturn(Optional.of(carrito));

        Carrito resultado = carritoService.obtenerCarritoActivo(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUsuarioId());
        assertEquals("ACTIVO", resultado.getEstado());
    }
}