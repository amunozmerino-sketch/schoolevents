package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.modelos.Notificacion;
import es.safareyes.schoolevents.repositorios.UsuarioInscritoRepository;
import es.safareyes.schoolevents.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioInscritoRepository usuarioInscritoRepository;

    @Test
    void consultarAgendaPersonal() {
        List<Evento> eventosSeguidos = usuarioRepository.buscarEventosSeguidos(10);
        List<Evento> eventosInscritos = usuarioInscritoRepository.buscarEventosInscritos(10);

        System.out.println("Eventos seguidos:");

        eventosSeguidos.forEach(es -> System.out.println(
                    "Título: " + es.getTitulo() + "\n"
                )
        );

        System.out.println("Eventos inscritos:");

        eventosInscritos.forEach(ei -> System.out.println(
                    "Título: " + ei.getTitulo() + "\n"
                )
        );
    }

    @Test
    void consultarNotificaciones() {
        List<Notificacion> notificaciones = usuarioRepository.buscarNotificaciones(5);

        notificaciones.forEach(n -> System.out.println(
                    "Mensaje: " + n.getMensaje() + " | " +
                    "Fecha y hora: " + n.getFechaHora() + "\n"
                )
        );
    }
}