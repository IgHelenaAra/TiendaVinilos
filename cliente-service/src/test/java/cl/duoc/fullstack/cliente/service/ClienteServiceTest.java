package cl.duoc.fullstack.cliente.service;

import cl.duoc.fullstack.cliente.exception.ResourceNotFoundException;
import cl.duoc.fullstack.cliente.model.Cliente;
import cl.duoc.fullstack.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    @Test
    void buscarPorId_debeRetornarRegistroCuandoExiste() {
        Cliente entidad = new Cliente();
        entidad.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entidad));

        Cliente resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        verify(repository).findById(1L);
    }

    @Test
    void buscarPorId_debeLanzarExcepcionCuandoNoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPorId(99L));
    }
}
