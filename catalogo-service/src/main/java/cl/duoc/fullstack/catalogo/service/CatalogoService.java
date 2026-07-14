package cl.duoc.fullstack.catalogo.service;

import cl.duoc.fullstack.catalogo.dto.CatalogoResponse;
import cl.duoc.fullstack.catalogo.dto.DestacadoRequest;
import cl.duoc.fullstack.catalogo.model.Destacado;
import cl.duoc.fullstack.catalogo.repository.DestacadoRepository;
import cl.duoc.fullstack.catalogo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoService {

    private final DestacadoRepository repository;
    private final WebClient.Builder webClientBuilder;

    @Transactional(readOnly = true)
    public List<CatalogoResponse> obtenerCatalogoDestacados() {
        List<Destacado> destacados = repository.findByActivoTrueOrderByPosicionAsc();

        return destacados.stream().map(destacado -> {
            Object detalles = null;
            try {
                detalles = webClientBuilder.build()
                        .get()
                        .uri("http://producto-service/api/vinilos")
                        .retrieve()
                        .bodyToMono(Object.class)
                        .block();
            } catch (Exception e) {
                detalles = "Información no disponible temporalmente";
            }

            return CatalogoResponse.builder()
                    .idDestacado(destacado.getId())
                    .posicion(destacado.getPosicion())
                    .skuVinilo(destacado.getSkuVinilo())
                    .detallesVinilo(detalles)
                    .build();
        }).collect(Collectors.toList());
    }

    @Transactional
    public Destacado agregarDestacado(DestacadoRequest request) {
        if (repository.findBySkuVinilo(request.getSkuVinilo()).isPresent()) {
            throw new IllegalArgumentException("El vinilo ya está en destacados.");
        }
        Destacado nuevo = Destacado.builder()
                .skuVinilo(request.getSkuVinilo())
                .posicion(request.getPosicion())
                .activo(true)
                .build();
        return repository.save(nuevo);
    }
}