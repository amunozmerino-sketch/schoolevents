package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.FotoPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPublicacionRepository extends JpaRepository<FotoPublicacion, Integer> {

}