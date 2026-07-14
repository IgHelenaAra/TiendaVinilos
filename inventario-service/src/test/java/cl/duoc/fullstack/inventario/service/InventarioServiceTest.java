package cl.duoc.fullstack.inventario.service;

import cl.duoc.fullstack.inventario.dto.InventarioRequest;
import cl.duoc.fullstack.inventario.model.Inventario;
import cl.duoc.fullstack.inventario.repository.InventarioRepository;
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
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void registrarInventario_Exito() {
        InventarioRequest request = new InventarioRequest();
        request.setSkuVinilo("VIN-TEST");
        request.setCantidadDisponible(10);
        request.setUbicacionBodega("Bodega 1");
        when(inventarioRepository.findBySkuVinilo("VIN-TEST")).thenReturn(Optional.empty());
        when(inventarioRepository.save(any(Inventario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Inventario resultado = inventarioService.registrarInventario(request);
        assertNotNull(resultado);
        assertEquals(10, resultado.getCantidadDisponible());
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void actualizarStock_FallaPorStockInsuficiente() {
        Inventario inventarioExistente = new Inventario();
        inventarioExistente.setSkuVinilo("VIN-TEST");
        inventarioExistente.setCantidadDisponible(5);

        when(inventarioRepository.findBySkuVinilo("VIN-TEST")).thenReturn(Optional.of(inventarioExistente));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inventarioService.actualizarStock("VIN-TEST", -10);
        });

        assertEquals("Stock insuficiente para el vinilo SKU: VIN-TEST", exception.getMessage());
        verify(inventarioRepository, never()).save(any(Inventario.class));
    }
}