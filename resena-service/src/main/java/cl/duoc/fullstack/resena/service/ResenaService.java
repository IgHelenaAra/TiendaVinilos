package cl.duoc.fullstack.resena.service;

import cl.duoc.fullstack.resena.dto.ResenaRequest;
import cl.duoc.fullstack.resena.model.Resena;
import cl.duoc.fullstack.resena.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;

    @Transactional
    public Resena crearResena(ResenaRequest request) {
        Resena nuevaResena = Resena.builder()
                .productoId(request.getProductoId())
                .calificacion(request.getCalificacion())
                .comentario(request.getComentario())
                .build();
        return resenaRepository.save(nuevaResena);
    }

    @Transactional(readOnly = true)
    public List<Resena> obtenerPorProducto(String productoId) {
        return resenaRepository.findByProductoId(productoId);
    }

    @Transactional(readOnly = true)
    public List<Resena> obtenerTodas() {
        return resenaRepository.findAll();
    }
}