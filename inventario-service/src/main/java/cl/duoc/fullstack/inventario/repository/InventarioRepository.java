package cl.duoc.fullstack.inventario.repository;

import cl.duoc.fullstack.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findBySkuVinilo(String skuVinilo);
}