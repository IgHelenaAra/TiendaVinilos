package cl.duoc.fullstack.catalogo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "destacados")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Destacado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sku_vinilo", nullable = false, unique = true)
    private String skuVinilo;

    @Column(nullable = false)
    private Integer posicion;

    @Column(nullable = false)
    private Boolean activo;
}