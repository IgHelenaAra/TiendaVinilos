package cl.duoc.fullstack.pedido.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PedidoItemRequest {
    @NotBlank(message = "El SKU del vinilo es obligatorio")
    private String skuVinilo;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 1, message = "El precio no puede ser negativo")
    private Integer precioUnitario;
}