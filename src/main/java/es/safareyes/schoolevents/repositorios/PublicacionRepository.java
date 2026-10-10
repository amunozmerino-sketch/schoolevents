package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.PublicacionConFotos;
import es.safareyes.schoolevents.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    @Query(value = """
    SELECT u.nombre || ' ' || u.apellidos as usuario, u.rol, p.texto, p.fecha_hora, array_agg(fp.enlace) AS fotos
    FROM publicacion p JOIN usuario u ON u.id = p.id_usuario JOIN foto_publicacion fp ON fp.id_publicacion = p.id
    WHERE p.id_evento = :idEvento
    GROUP BY usuario, u.rol, p.texto, p.fecha_hora;
    """, nativeQuery = true)
    List<PublicacionConFotos> buscarPublicacionesPorEvento(@Param("idEvento") Integer idEvento);
}