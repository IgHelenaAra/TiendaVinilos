package cl.duoc.fullstack.pedido.repository;

import cl.duoc.fullstack.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByRutCliente(String rutCliente);
}