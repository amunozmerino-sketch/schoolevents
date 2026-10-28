package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.response.PublicacionConFotosResponse;
import es.safareyes.schoolevents.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    @Query(value = """
    SELECT p
    FROM Publicacion p JOIN p.usuario u JOIN p.fotos f
    WHERE p.evento.id = :idEvento
    """)
    List<Publicacion> buscarPublicacionesPorEvento(@Param("idEvento") Integer idEvento);
}