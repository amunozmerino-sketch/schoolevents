package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Evento;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("""
    SELECT DISTINCT e FROM Evento e
    WHERE (:listaCategorias IS NULL OR e.categoria IN :listaCategorias)
        AND (:listaCursos IS NULL OR e.curso IN :listaCursos)
        AND (:texto IS NULL OR LOWER(e.titulo) LIKE LOWER(:texto))
        AND (:fechaIni IS NULL OR (e.fechaHoraIni = :fechaIni OR e.fechaHoraIni BETWEEN :fechaIni AND :fechaFin))
""")
    List<Evento> buscarEventosFiltrados();


}