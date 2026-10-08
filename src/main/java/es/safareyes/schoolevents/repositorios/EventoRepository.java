package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Evento;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query("""
    SELECT DISTINCT e FROM Evento e
    WHERE e.categoria IN :listaCategorias
        OR e.curso IN :listaCursos
        OR LOWER(e.titulo) LIKE LOWER(:texto)
        OR e.fechaHoraIni IN :listaFechas
    """)
    List<Evento> buscarEventosFiltrados();

    @Query(value = """
    SELECT EXTRACT(DAY FROM e.fecha_hora_ini) AS dia, e.titulo, c.icono, c.color
    FROM evento e JOIN categoria_evento ce ON e.id = ce.id_evento JOIN categoria c ON ce.id_categoria = c.id
    WHERE EXTRACT(MONTH FROM e.fecha_hora_ini) = :mes
    GROUP BY dia, e.titulo, c.icono, c.color
    ORDER BY dia;
    """, nativeQuery = true)
    List<Evento> eventosDelMesPorDia(@Param("mes") Integer mes);

    @Query("""
    SELECT e.lugar, r.descripcion, e.plazasLibres, c.nombre
    FROM Evento e JOIN e.requisito r JOIN e.curso c
    WHERE e.id = :idEvento
    """
    )
    List<Evento> detallesEvento(@Param("idEvento") Integer idEvento);
}