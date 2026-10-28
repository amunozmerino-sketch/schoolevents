package es.safareyes.schoolevents.modelos;

import es.safareyes.schoolevents.modelos.enums.RolUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usuario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "apellidos", length = 150)
    private String apellidos;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rol", precision = 1)
    private RolUsuario rol;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "contrasena", length = 20)
    private String contrasena;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "notificacion_usuario",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_notificacion")
    )
    private List<Notificacion> notificaciones;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_interesado_evento",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_evento")
    )
    private List<Evento> eventosInteresados;
}