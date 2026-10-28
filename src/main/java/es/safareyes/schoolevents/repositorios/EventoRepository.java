package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento> {

    @Query(value = """
    SELECT e.*
    FROM evento e
    WHERE EXTRACT(MONTH FROM e.fecha_hora_ini) = :mes AND EXTRACT(YEAR FROM e.fecha_hora_ini) = :ano
    ORDER BY e.fecha_hora_ini;
    """, nativeQuery = true)
    List<Evento> eventosDelMesPorDia(@Param("mes") Integer mes,
                                     @Param("ano") Integer ano);

    @Query(value = """
    SELECT e
    FROM Evento e
    	JOIN e.lugar l
    	JOIN e.cursos c
    	JOIN e.requisitos r
    WHERE e.id = :idEvento
    """)
    Evento detallesEvento(@Param("idEvento") Integer idEvento);
}