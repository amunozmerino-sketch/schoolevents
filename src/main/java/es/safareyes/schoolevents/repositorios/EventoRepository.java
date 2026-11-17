package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dto.response.EventoCalendarioNativoResponse;
import es.safareyes.schoolevents.modelos.Evento;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento> {

    @Query(value = """
    SELECT e.id, e.titulo, l.nombre, c.icono, c.color, e.fecha_hora_ini, e.fecha_hora_fin
    FROM evento e
            JOIN lugar l ON e.id_lugar = l.id
            JOIN categoria c ON e.id_categoria = c.id
    WHERE EXTRACT(MONTH FROM e.fecha_hora_ini) = :mes AND EXTRACT(YEAR FROM e.fecha_hora_ini) = :ano
    ORDER BY e.fecha_hora_ini;
    """, nativeQuery = true)
    List<EventoCalendarioNativoResponse> eventosDelMesPorDia(@Param("mes") Integer mes,
                                                             @Param("ano") Integer ano);

    @EntityGraph(attributePaths = {"categoria"})
    List<Evento> findAllByCategoria_IdOrderByFechaHoraIni(Integer idCategoria);

    @EntityGraph(attributePaths = {"categoria"})
    List<Evento> findAllByOrderByFechaHoraIni();

    @NullMarked
    @EntityGraph(attributePaths = {"categoria", "cursos"})
    List<Evento> findAll(Specification<Evento> spec);

    @NullMarked
    @EntityGraph(attributePaths = {"categoria", "cursos", "requisitos", "lugar", "usuarioAutor"})
    Optional<Evento> findById(Integer id);
}