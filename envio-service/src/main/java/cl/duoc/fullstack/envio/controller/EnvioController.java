package cl.duoc.fullstack.envio.controller;

import cl.duoc.fullstack.envio.dto.EnvioRequest;
import cl.duoc.fullstack.envio.model.Envio;
import cl.duoc.fullstack.envio.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listarEnvios() {
        return ResponseEntity.ok(envioService.obtenerTodos());
    }

    @GetMapping("/seguimiento/{codigo}")
    public ResponseEntity<Envio> obtenerPorSeguimiento(@PathVariable String codigo) {
        return ResponseEntity.ok(envioService.obtenerPorCodigoSeguimiento(codigo));
    }

    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@Valid @RequestBody EnvioRequest request) {
        Envio envioCreado = envioService.crearEnvio(request);
        return new ResponseEntity<>(envioCreado, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        return ResponseEntity.ok(envioService.actualizarEstado(id, estado));
    }
}