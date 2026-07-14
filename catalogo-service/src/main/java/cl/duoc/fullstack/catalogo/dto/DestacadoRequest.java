package cl.duoc.fullstack.catalogo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DestacadoRequest {
    @NotBlank(message = "El SKU del vinilo es obligatorio")
    private String skuVinilo;

    @NotNull(message = "La posición es obligatoria")
    @Min(value = 1, message = "La posición debe ser al menos 1")
    private Integer posicion;
}