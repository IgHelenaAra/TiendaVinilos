package cl.duoc.fullstack.inventario.service;

import cl.duoc.fullstack.inventario.dto.InventarioRequest;
import cl.duoc.fullstack.inventario.model.Inventario;
import cl.duoc.fullstack.inventario.repository.InventarioRepository;
import cl.duoc.fullstack.inventario.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    @Transactional(readOnly = true)
    public List<Inventario> obtenerTodoElStock() {
        return inventarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Inventario obtenerStockPorSku(String sku) {
        return inventarioRepository.findBySkuVinilo(sku)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró inventario para el SKU: " + sku));
    }

    @Transactional
    public Inventario registrarInventario(InventarioRequest request) {
        if (inventarioRepository.findBySkuVinilo(request.getSkuVinilo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un registro de inventario para el SKU: " + request.getSkuVinilo());
        }

        Inventario nuevoInventario = Inventario.builder()
                .skuVinilo(request.getSkuVinilo())
                .cantidadDisponible(request.getCantidadDisponible())
                .ubicacionBodega(request.getUbicacionBodega())
                .build();

        return inventarioRepository.save(nuevoInventario);
    }

    @Transactional
    public Inventario actualizarStock(String sku, Integer variacion) {
        Inventario inventario = obtenerStockPorSku(sku);
        int nuevoStock = inventario.getCantidadDisponible() + variacion;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("Stock insuficiente para el vinilo SKU: " + sku);
        }

        inventario.setCantidadDisponible(nuevoStock);
        return inventarioRepository.save(inventario);
    }
}