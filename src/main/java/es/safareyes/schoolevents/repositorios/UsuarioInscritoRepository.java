package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.response.ParticipacionDto;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.modelos.Usuario;
import es.safareyes.schoolevents.modelos.UsuarioInscrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsuarioInscritoRepository extends JpaRepository<UsuarioInscrito, Integer> {
    @Query("""
    SELECT e
    FROM UsuarioInscrito ui JOIN ui.evento e
    WHERE ui.usuario.id = :idUsuario
    """)
    List<Evento> buscarEventosInscritos(@Param("idUsuario") Integer idUsuario);

    @Query("""
    SELECT ui
    FROM UsuarioInscrito ui
    WHERE ui.evento.id = :idEvento AND ui.enEspera = :enEspera
    """)
    List<UsuarioInscrito> inscripciones(@Param("idEvento") Integer idEvento,
                                        @Param("enEspera") Boolean enEspera);

    @Query(value = """
    SELECT c.nombre as categoria, cu.nombre as curso, count(uie.id_usuario)
    FROM usuario_inscrito_evento uie
        JOIN evento e ON e.id = uie.id_evento
        JOIN usuario u ON u.id = uie.id_usuario
        JOIN categoria c ON e.id_categoria = c.id
        JOIN curso cu ON u.id_curso = cu.id
    WHERE uie.fecha_hora BETWEEN :desde AND :hasta
    GROUP BY categoria, curso
    ORDER BY categoria;
    """, nativeQuery = true)
    List<ParticipacionDto> informeParticipacion(@Param("desde")LocalDateTime desde,
                                                @Param("hasta") LocalDateTime hasta);
}