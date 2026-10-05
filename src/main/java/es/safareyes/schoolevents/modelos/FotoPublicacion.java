package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "foto_publicacion")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FotoPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "enlace", length = 150)
    private String enlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_publicacion")
    private Publicacion publicacion;
}