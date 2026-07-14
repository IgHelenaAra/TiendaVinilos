package cl.duoc.fullstack.resena.service;

import cl.duoc.fullstack.resena.dto.ResenaRequest;
import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void crearResena_Exito() {
        // Given
        ResenaRequest request = new ResenaRequest();
        request.setProductoId("VIN-DARK-SIDE");
        request.setCalificacion(5);
        request.setComentario("Excelente sonido");

        when(resenaRepository.save(any(Resena.class))).thenAnswer(i -> i.getArgument(0));


        Resena resultado = resenaService.crearResena(request);


        assertNotNull(resultado);
        assertEquals("VIN-DARK-SIDE", resultado.getProductoId());
        verify(resenaRepository, times(1)).save(any(Resena.class));
    }

    @Test
    void obtenerPorProducto_Exito() {

        Resena resena = new Resena();
        resena.setProductoId("VIN-DARK-SIDE");
        resena.setCalificacion(4);

        when(resenaRepository.findByProductoId("VIN-DARK-SIDE")).thenReturn(List.of(resena));


        List<Resena> resultado = resenaService.obtenerPorProducto("VIN-DARK-SIDE");

        assertFalse(resultado.isEmpty());
        assertEquals("VIN-DARK-SIDE", resultado.get(0).getProductoId());
        verify(resenaRepository, times(1)).findByProductoId("VIN-DARK-SIDE");
    }
}