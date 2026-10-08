package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Evento;
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

    @Test
    void consultarAgendaPersonal() {
        List<Evento> eventosSeguidos = usuarioRepository.buscarEventosSeguidos(10);
        List<Evento> eventosInscritos = usuarioRepository.buscarEventosInscritos(10);

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
}