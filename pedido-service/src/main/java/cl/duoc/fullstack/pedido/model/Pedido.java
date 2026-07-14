package cl.duoc.fullstack.pedido.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rut_cliente", nullable = false, length = 50)
    private String rutCliente;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(name = "fecha_pedido", updatable = false)
    private LocalDateTime fechaPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PedidoItem> items = new ArrayList<>();

    @PrePersist
    protected void onCreate() { fechaPedido = LocalDateTime.now(); }
}