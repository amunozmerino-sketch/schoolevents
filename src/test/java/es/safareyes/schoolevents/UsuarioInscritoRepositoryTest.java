package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dto.InformeParticipacion;
import es.safareyes.schoolevents.modelos.Usuario;
import es.safareyes.schoolevents.repositorios.UsuarioInscritoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioInscritoRepositoryTest {
    @Autowired
    private UsuarioInscritoRepository usuarioInscritoRepository;

    @Test
    void consultarInscripciones() {
        List<Usuario> usuarioInscritos = usuarioInscritoRepository.inscripciones(8, false);
        List<Usuario> listaDeEspera = usuarioInscritoRepository.inscripciones(8, true);

        System.out.println("Usuarios inscritos:");

        usuarioInscritos.forEach(ui ->
                System.out.println(
                        "Nombre: " + ui.getNombre() + " " + ui.getApellidos() + "\n"
                )
        );

        System.out.println("Lista de espera:");

        listaDeEspera.forEach(le ->
                System.out.println(
                        "Nombre: " + le.getNombre() + " " + le.getApellidos() + "\n"
                )
        );
    }

    @Test
    void generarInformeParticipacion() {
        LocalDateTime desde = LocalDateTime.of(2026, 9, 1, 0,0,0);
        LocalDateTime hasta = LocalDateTime.of(2026, 10, 31, 23,59,59);

        List<InformeParticipacion> informeParticipacion = usuarioInscritoRepository.informeParticipacion(desde, hasta);

        informeParticipacion.forEach(ip ->
                System.out.println(
                        "Categoría: " + ip.getCategoria() + " | " +
                        "Curso: " + ip.getCurso() + " | " +
                        "Participación: " + ip.getParticipacion() + "\n"
                )
        );
    }
}