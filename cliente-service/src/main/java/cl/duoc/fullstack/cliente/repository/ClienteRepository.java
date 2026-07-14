package cl.duoc.fullstack.cliente.repository;

import cl.duoc.fullstack.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
