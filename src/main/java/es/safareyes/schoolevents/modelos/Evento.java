package es.safareyes.schoolevents.modelos;

import es.safareyes.schoolevents.modelos.enums.EstadoEvento;
import es.safareyes.schoolevents.validaciones.PublicarEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "evento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotBlank(groups = PublicarEvento.class, message = "El título es obligatoria")
    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @NotBlank(groups = PublicarEvento.class, message = "La descripción es obligatoria")
    @Column(name = "descripcion")
    private String descripcion;

    @NotBlank(groups = PublicarEvento.class, message = "La foto es obligatoria")
    @Column(name = "foto", length = 150)
    private String foto;

    @NotNull(groups = PublicarEvento.class, message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_hora_ini")
    private LocalDateTime fechaHoraIni;

    @NotNull(groups = PublicarEvento.class, message = "La fecha fin es obligatoria")
    @Column(name = "fecha_hora_fin")
    private LocalDateTime fechaHoraFin;

    @NotNull(groups = PublicarEvento.class, message = "La fecha límite es obligatoria")
    @Column(name = "fecha_hora_limite")
    private LocalDateTime fechaHoraLimite;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado", precision = 1)
    private EstadoEvento estado;

    @NotNull(groups = PublicarEvento.class, message = "El aforo máximo es obligatorio")
    @Column(name = "aforo_max", precision = 3)
    private Integer aforoMax;

    @NotNull(groups = PublicarEvento.class, message = "El lugar es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "curso_evento",
            joinColumns = @JoinColumn(name = "id_evento"),
            inverseJoinColumns = @JoinColumn(name = "id_curso")
    )
    private List<Curso> cursos;

    @NotNull(groups = PublicarEvento.class, message = "La categoría es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Requisito> requisitos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_autor")
    private Usuario usuarioAutor;
}