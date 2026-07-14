package cl.duoc.fullstack.pedido.controller;

import cl.duoc.fullstack.pedido.dto.PedidoRequest;
import cl.duoc.fullstack.pedido.model.Pedido;
import cl.duoc.fullstack.pedido.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody PedidoRequest request) {
        return new ResponseEntity<>(pedidoService.crearPedido(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> obtenerTodos() {
        return ResponseEntity.ok(pedidoService.obtenerTodos());
    }

    @GetMapping("/cliente/{rut}")
    public ResponseEntity<List<Pedido>> obtenerPorCliente(@PathVariable String rut) {
        return ResponseEntity.ok(pedidoService.obtenerPedidosPorCliente(rut));
    }
}