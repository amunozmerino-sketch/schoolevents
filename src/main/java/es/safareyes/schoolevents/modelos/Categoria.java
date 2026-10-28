package es.safareyes.schoolevents.modelos;

import es.safareyes.schoolevents.modelos.enums.ColorCategoria;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "icono", length = 150)
    private String icono;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "color", precision = 1)
    private ColorCategoria color;
}