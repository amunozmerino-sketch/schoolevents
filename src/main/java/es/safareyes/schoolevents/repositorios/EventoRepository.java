package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.EventosDelMes;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.dto.DetallesEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query(value = """
    SELECT DISTINCT e.*
    FROM evento e
    JOIN categoria_evento ce ON e.id = ce.id_evento
    JOIN curso_evento ce2 ON e.id = ce2.id_evento
    WHERE (COALESCE(:cursos) IS NULL OR ce2.id_curso IN (:cursos))
      AND (COALESCE(:categorias) IS NULL OR ce.id_categoria IN (:categorias))
      AND (:titulo IS NULL OR e.titulo ILIKE CONCAT('%', :titulo, '%'))
      AND (CAST(:desde AS TIMESTAMP) IS NULL OR
          (e.fecha_hora_ini BETWEEN CAST(:desde AS TIMESTAMP) AND COALESCE(CAST(:hasta AS TIMESTAMP), CAST(:desde AS TIMESTAMP))))
      AND e.estado = 0;
    """, nativeQuery = true)
    List<Evento> buscarEventosFiltrados(@Param("cursos") List<Integer> cursos,
                                        @Param("categorias") List<Integer> categorias,
                                        @Param("titulo") String titulo,
                                        @Param("desde") LocalDateTime desde,
                                        @Param("hasta") LocalDateTime hasta);

    @Query(value = """
    SELECT EXTRACT(DAY FROM e.fecha_hora_ini) AS dia, e.titulo, c.icono, c.color
    FROM evento e JOIN categoria_evento ce ON e.id = ce.id_evento JOIN categoria c ON ce.id_categoria = c.id
    WHERE EXTRACT(MONTH FROM e.fecha_hora_ini) = :mes AND EXTRACT(YEAR FROM e.fecha_hora_ini) = :ano
    GROUP BY dia, e.titulo, c.icono, c.color
    ORDER BY dia;
    """, nativeQuery = true)
    List<EventosDelMes> eventosDelMesPorDia(@Param("mes") Integer mes,
                                            @Param("ano") Integer ano);

    @Query(value = """
    SELECT l.nombre AS lugar, array_agg(DISTINCT r.descripcion) AS requisitos, e.plazas_libres AS plazasLibres, array_agg(c.nombre) AS cursos
    FROM evento e
    	JOIN lugar l ON e.id_lugar = l.id
    	JOIN curso_evento ce ON ce.id_evento = e.id
    	JOIN curso c ON c.id = ce.id_curso
    	JOIN requisito r ON r.id_evento = e.id
    WHERE e.id = :idEvento
    GROUP BY e.id, l.nombre, e.plazas_libres;
    """, nativeQuery = true)
    DetallesEvento detallesEvento(@Param("idEvento") Integer idEvento);
}