package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.UsuarioInscrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioInscritoRepository extends JpaRepository<UsuarioInscrito, Integer> {

}