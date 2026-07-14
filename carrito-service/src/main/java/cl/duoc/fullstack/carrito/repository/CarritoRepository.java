package cl.duoc.fullstack.carrito.repository;
import cl.duoc.fullstack.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuarioIdAndEstado(Long usuarioId, String estado);
}