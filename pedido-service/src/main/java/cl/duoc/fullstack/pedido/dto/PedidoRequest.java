package cl.duoc.fullstack.pedido.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class PedidoRequest {
    @NotBlank(message = "El RUT del cliente es obligatorio")
    private String rutCliente;

    @NotEmpty(message = "El pedido debe tener al menos un ítem")
    private List<PedidoItemRequest> items;
}