package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lugar")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class Lugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "aforo_max", precision = 3)
    private Integer aforoMax;
}