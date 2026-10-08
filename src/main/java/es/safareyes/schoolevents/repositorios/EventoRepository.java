package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.EventosDelMes;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.dto.DetallesEvento;
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

//    @Query("""
//    SELECT DISTINCT e FROM Evento e
//    WHERE e.categoria IN :listaCategorias
//        OR e.curso IN :listaCursos
//        OR LOWER(e.titulo) LIKE LOWER(:texto)
//        OR e.fechaHoraIni IN :listaFechas
//    """)
//    List<Evento> buscarEventosFiltrados();

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
    List<DetallesEvento> detallesEvento(@Param("idEvento") Integer idEvento);
}