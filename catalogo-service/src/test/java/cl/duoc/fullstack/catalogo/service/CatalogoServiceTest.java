package cl.duoc.fullstack.catalogo.service;

import cl.duoc.fullstack.catalogo.dto.DestacadoRequest;
import cl.duoc.fullstack.catalogo.model.Destacado;
import cl.duoc.fullstack.catalogo.repository.DestacadoRepository;
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
class CatalogoServiceTest {

    @Mock
    private DestacadoRepository repository;

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private CatalogoService service;

    @Test
    void agregarDestacado_Exito() {
        DestacadoRequest request = new DestacadoRequest();
        request.setSkuVinilo("VIN-TEST");
        request.setPosicion(1);

        when(repository.findBySkuVinilo("VIN-TEST")).thenReturn(Optional.empty());
        when(repository.save(any(Destacado.class))).thenAnswer(i -> i.getArgument(0));

        Destacado resultado = service.agregarDestacado(request);

        assertNotNull(resultado);
        assertTrue(resultado.getActivo());
        assertEquals("VIN-TEST", resultado.getSkuVinilo());
        verify(repository, times(1)).save(any(Destacado.class));
    }

    @Test
    void agregarDestacado_FallaDuplicado() {
        DestacadoRequest request = new DestacadoRequest();
        request.setSkuVinilo("VIN-TEST");

        Destacado existente = new Destacado();
        existente.setSkuVinilo("VIN-TEST");

        when(repository.findBySkuVinilo("VIN-TEST")).thenReturn(Optional.of(existente));

        assertThrows(IllegalArgumentException.class, () -> service.agregarDestacado(request));
        verify(repository, never()).save(any(Destacado.class));
    }
}