package cl.duoc.fullstack.pago.controller;

import cl.duoc.fullstack.pago.dto.PagoRequest;
import cl.duoc.fullstack.pago.model.Pago;
import cl.duoc.fullstack.pago.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final PagoService service;

    @GetMapping("/")
    public CollectionModel<EntityModel<Pago>> listar() {
        List<EntityModel<Pago>> recursos = service.listar().stream().map(this::toModel).toList();
        return CollectionModel.of(recursos, linkTo(methodOn(PagoController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pago> buscar(@PathVariable Long id) {
        return toModel(service.buscarPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<Pago>> crear(@Valid @RequestBody PagoRequest request) {
        Pago creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(creado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<Pago> toModel(Pago entidad) {
        return EntityModel.of(entidad,
                linkTo(methodOn(PagoController.class).buscar(entidad.getId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).listar()).withRel("todos"));
    }
}
