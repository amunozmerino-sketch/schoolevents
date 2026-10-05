package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "curso")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor @Builder
@ToString
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 150)
    private String nombre;
}