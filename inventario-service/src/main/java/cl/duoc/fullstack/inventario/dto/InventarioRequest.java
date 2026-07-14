package cl.duoc.fullstack.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventarioRequest {

    @NotBlank(message = "El SKU del vinilo es obligatorio")
    private String skuVinilo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer cantidadDisponible;

    @NotBlank(message = "La ubicación de la bodega es obligatoria")
    private String ubicacionBodega;
}