package cl.duoc.fullstack.inventario.controller;

import cl.duoc.fullstack.inventario.dto.InventarioRequest;
import cl.duoc.fullstack.inventario.model.Inventario;
import cl.duoc.fullstack.inventario.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.obtenerTodoElStock());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Inventario> consultarStock(@PathVariable String sku) {
        return ResponseEntity.ok(inventarioService.obtenerStockPorSku(sku));
    }

    @PostMapping
    public ResponseEntity<Inventario> registrarInventario(@Valid @RequestBody InventarioRequest request) {
        return new ResponseEntity<>(inventarioService.registrarInventario(request), HttpStatus.CREATED);
    }

    @PatchMapping("/{sku}/ajustar")
    public ResponseEntity<Inventario> ajustarStock(@PathVariable String sku, @RequestParam Integer variacion) {
        return ResponseEntity.ok(inventarioService.actualizarStock(sku, variacion));
    }
}