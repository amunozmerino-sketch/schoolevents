package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}