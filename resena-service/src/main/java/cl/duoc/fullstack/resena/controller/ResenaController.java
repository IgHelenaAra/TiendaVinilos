package cl.duoc.fullstack.resena.controller;

import cl.duoc.fullstack.resena.dto.ResenaRequest;
import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.service.ResenaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
@Tag(name = "Reseñas", description = "API para la gestión de reseñas de productos")
public class ResenaController {

    private final ResenaService resenaService;

    @PostMapping
    @Operation(summary = "Crear una nueva reseña", description = "Crea una reseña asociada a un ID de producto")
    @ApiResponse(responseCode = "201", description = "Reseña creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    public ResponseEntity<Resena> crearResena(@Valid @RequestBody ResenaRequest request) {
        return new ResponseEntity<>(resenaService.crearResena(request), HttpStatus.CREATED);
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "Obtener reseñas por producto", description = "Lista todas las reseñas de un producto específico")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    public ResponseEntity<List<Resena>> obtenerResenas(@PathVariable String productoId) {
        return ResponseEntity.ok(resenaService.obtenerPorProducto(productoId));
    }
}