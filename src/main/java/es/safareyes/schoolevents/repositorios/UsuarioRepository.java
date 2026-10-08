package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("""
    SELECT e
    FROM Usuario u JOIN u.eventoInteresado e
    WHERE u.id = :idUsuario
    """)
    List<Evento> buscarEventosSeguidos(@Param("idUsuario") Integer idUsuario);

    @Query("""
    SELECT e
    FROM Usuario u JOIN u.inscripcion i JOIN i.evento e
    WHERE u.id = :idUsuario
    """)
    List<Evento> buscarEventosInscritos(@Param("idUsuario") Integer idUsuario);
}