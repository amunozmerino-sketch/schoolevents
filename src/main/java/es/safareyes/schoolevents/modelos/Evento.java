package es.safareyes.schoolevents.modelos;

import es.safareyes.schoolevents.enums.EstadoEvento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evento")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_hora_ini")
    private LocalDateTime fechaHoraIni;

    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @Column(name = "fecha_hora_limite")
    private LocalDateTime fechaHoraLimite;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado", precision = 1)
    private EstadoEvento estado;

    @Column(name = "aforo_max", precision = 3)
    private Integer aforoMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "curso_evento",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<Curso> curso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "categoria_evento",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categoria;
}