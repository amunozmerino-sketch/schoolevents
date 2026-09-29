package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_hora_ini")
    private Date fechaHoraIni;

    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;

    @Column(name = "fecha_hora_limite")
    private Date fechaHoraLimite;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "aforo_max")
    private Integer aforoMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lugar")
    private Lugar lugar;
}