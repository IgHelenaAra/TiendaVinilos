package cl.duoc.fullstack.pago.service;

import cl.duoc.fullstack.pago.exception.ResourceNotFoundException;
import cl.duoc.fullstack.pago.model.Pago;
import cl.duoc.fullstack.pago.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {
    @Mock
    private PagoRepository repository;

    @InjectMocks
    private PagoService service;

    @Test
    void buscarPorId_debeRetornarRegistroCuandoExiste() {
        Pago entidad = new Pago();
        entidad.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entidad));

        Pago resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        verify(repository).findById(1L);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
