package cl.duoc.fullstack.cliente.service;

import cl.duoc.fullstack.cliente.dto.ClienteRequest;
import cl.duoc.fullstack.cliente.exception.ResourceNotFoundException;
import cl.duoc.fullstack.cliente.model.Cliente;
import cl.duoc.fullstack.cliente.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public List<Cliente> listar() {
        log.info("Listando clientes");
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe cliente con ID " + id));
    }

    public Cliente crear(ClienteRequest request) {
        Cliente entidad = new Cliente();
        entidad.setRut(request.getRut());
        entidad.setNombre(request.getNombre());
        entidad.setEmail(request.getEmail());
        entidad.setTelefono(request.getTelefono());
        log.info("Creando cliente: {}", request);
        return repository.save(entidad);
    }

    public Cliente actualizar(Long id, ClienteRequest request) {
        Cliente entidad = buscarPorId(id);
        entidad.setRut(request.getRut());
        entidad.setNombre(request.getNombre());
        entidad.setEmail(request.getEmail());
        entidad.setTelefono(request.getTelefono());
        log.info("Actualizando cliente ID {}", id);
        return repository.save(entidad);
    }

    public void eliminar(Long id) {
        Cliente entidad = buscarPorId(id);
        repository.delete(entidad);
        log.info("Eliminado cliente ID {}", id);
    }
}
