package cl.duoc.fullstack.pago.repository;

import cl.duoc.fullstack.pago.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
