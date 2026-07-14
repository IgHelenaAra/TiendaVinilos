package cl.duoc.fullstack.envio.repository;

import cl.duoc.fullstack.envio.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    Optional<Envio> findByPedidoId(Long pedidoId);
    Optional<Envio> findByCodigoSeguimiento(String codigoSeguimiento);
}