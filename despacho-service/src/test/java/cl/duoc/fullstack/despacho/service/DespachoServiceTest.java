package cl.duoc.fullstack.despacho.service;

import cl.duoc.fullstack.despacho.exception.ResourceNotFoundException;
import cl.duoc.fullstack.despacho.model.Despacho;
import cl.duoc.fullstack.despacho.repository.DespachoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DespachoServiceTest {
    @Mock
    private DespachoRepository repository;

    @InjectMocks
    private DespachoService service;

    @Test
    void buscarPorId_debeRetornarRegistroCuandoExiste() {
        Despacho entidad = new Despacho();
        entidad.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entidad));

        Despacho resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        verify(repository).findById(1L);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
