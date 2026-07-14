package cl.duoc.fullstack.cliente.controller;

import cl.duoc.fullstack.cliente.dto.ClienteRequest;
import cl.duoc.fullstack.cliente.model.Cliente;
import cl.duoc.fullstack.cliente.service.ClienteService;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @GetMapping("/")
    public CollectionModel<EntityModel<Cliente>> listar() {
        List<EntityModel<Cliente>> recursos = service.listar().stream().map(this::toModel).toList();
        return CollectionModel.of(recursos, linkTo(methodOn(ClienteController.class).listar()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Cliente> buscar(@PathVariable Long id) {
        return toModel(service.buscarPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<EntityModel<Cliente>> crear(@Valid @RequestBody ClienteRequest request) {
        Cliente creado = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(creado));
    }


    @PutMapping("/{id}")
    public EntityModel<Cliente> actualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return toModel(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private EntityModel<Cliente> toModel(Cliente entidad) {
        return EntityModel.of(entidad,
                linkTo(methodOn(ClienteController.class).buscar(entidad.getId())).withSelfRel(),
                linkTo(methodOn(ClienteController.class).listar()).withRel("todos"));
    }
}
