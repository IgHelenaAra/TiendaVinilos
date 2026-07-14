package cl.duoc.fullstack.catalogo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogoResponse {
    private Long idDestacado;
    private Integer posicion;
    private String skuVinilo;
    private Object detallesVinilo; // Aquí guardaremos el JSON que nos responda el otro microservicio
}