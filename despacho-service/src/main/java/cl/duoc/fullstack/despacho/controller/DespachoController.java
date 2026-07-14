package cl.duoc.fullstack.despacho.controller;

import cl.duoc.fullstack.despacho.dto.DespachoRequest;
import cl.duoc.fullstack.despacho.model.Despacho;
import cl.duoc.fullstack.despacho.service.DespachoService;
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
@RequestMapping("/api/despachos")
@RequiredArgsConstructor
public class DespachoController {
    private final DespachoService service;

    @GetMapping("/")
    public CollectionModel<EntityModel<Despacho>> listar() {
        List<EntityModel<Despacho>> recursos = service.listar().stream().map(this::toModel).toList();
        return CollectionModel.of(recursos, linkTo(methodOn(DespachoController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Despacho> buscar(@PathVariable Long id) {
        return toModel(service.buscarPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<Despacho>> crear(@Valid @RequestBody DespachoRequest request) {
        Despacho creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(creado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<Despacho> toModel(Despacho entidad) {
        return EntityModel.of(entidad,
                linkTo(methodOn(DespachoController.class).buscar(entidad.getId())).withSelfRel(),
                linkTo(methodOn(DespachoController.class).listar()).withRel("todos"));
    }
}
