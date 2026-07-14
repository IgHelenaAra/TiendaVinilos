package cl.duoc.fullstack.catalogo.controller;

import cl.duoc.fullstack.catalogo.dto.CatalogoResponse;
import cl.duoc.fullstack.catalogo.dto.DestacadoRequest;
import cl.duoc.fullstack.catalogo.model.Destacado;
import cl.duoc.fullstack.catalogo.service.CatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
@RequiredArgsConstructor
public class CatalogoController {

    private final CatalogoService catalogoService;

    @GetMapping("/destacados")
    public ResponseEntity<List<CatalogoResponse>> verDestacados() {
        return ResponseEntity.ok(catalogoService.obtenerCatalogoDestacados());
    }

    @PostMapping("/destacados")
    public ResponseEntity<Destacado> agregarDestacado(@Valid @RequestBody DestacadoRequest request) {
        return new ResponseEntity<>(catalogoService.agregarDestacado(request), HttpStatus.CREATED);
    }
}