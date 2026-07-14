package cl.duoc.fullstack.pago.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {
    @NotNull(message = "El pedido es obligatorio") private Long pedidoId;
    @NotBlank(message = "El método de pago es obligatorio") private String metodo;
    @Min(value = 1, message = "El monto debe ser mayor a cero") private Integer monto;
}
