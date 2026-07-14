package cl.duoc.fullstack.carrito.controller;
import cl.duoc.fullstack.carrito.dto.AgregarItemRequest;
import cl.duoc.fullstack.carrito.model.Carrito;
import cl.duoc.fullstack.carrito.service.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Carrito> obtenerCarritoActivo(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerCarritoActivo(usuarioId));
    }

    @PostMapping("/items")
    public ResponseEntity<Carrito> agregarAlCarrito(@Valid @RequestBody AgregarItemRequest request) {
        return new ResponseEntity<>(carritoService.agregarItem(request), HttpStatus.CREATED);
    }
}