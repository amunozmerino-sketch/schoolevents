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
    WHERE e.categoria IN :listaCategorias
        OR e.curso IN :listaCursos
        OR LOWER(e.titulo) LIKE LOWER(:texto)
        OR e.fechaHoraIni IN :listaFechas
""")
    List<Evento> buscarEventosFiltrados();
}