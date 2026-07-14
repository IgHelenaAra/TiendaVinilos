package cl.duoc.fullstack.despacho.repository;

import cl.duoc.fullstack.despacho.model.Despacho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespachoRepository extends JpaRepository<Despacho, Long> {
}
