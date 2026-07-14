package cl.duoc.fullstack.despacho.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DespachoRequest {
    @NotNull(message = "El pedido es obligatorio") private Long pedidoId;
    @NotBlank(message = "La dirección es obligatoria") private String direccion;
    @NotBlank(message = "La comuna es obligatoria") private String comuna;
}
