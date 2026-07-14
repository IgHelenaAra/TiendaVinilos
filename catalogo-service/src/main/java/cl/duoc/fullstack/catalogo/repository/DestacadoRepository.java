package cl.duoc.fullstack.catalogo.repository;

import cl.duoc.fullstack.catalogo.model.Destacado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DestacadoRepository extends JpaRepository<Destacado, Long> {
    List<Destacado> findByActivoTrueOrderByPosicionAsc();
    Optional<Destacado> findBySkuVinilo(String skuVinilo);
}