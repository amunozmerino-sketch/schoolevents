package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dto.DetallesEvento;
import es.safareyes.schoolevents.dto.EventosDelMes;
import es.safareyes.schoolevents.modelos.UsuarioInscrito;
import es.safareyes.schoolevents.repositorios.EventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventoRepositoryTest {
    @Autowired
    private EventoRepository eventoRepository;

    @Test
    void consultarDetallesEvento() {
        DetallesEvento detallesEvento = eventoRepository.detallesEvento(1);


        System.out.println(
                "Lugar: " + detallesEvento.getLugar() + " | " +
                "Cursos: " + Arrays.toString(detallesEvento.getCursos()) + " | " +
                "Plazas libres: " + detallesEvento.getPlazasLibres() + " | " +
                "Requisitos: " + Arrays.toString(detallesEvento.getRequisitos())
        );
    }

    @Test
    void consultarEventosDelMes() {
        List<EventosDelMes> eventosDelMes = eventoRepository.eventosDelMesPorDia(11, 2026);

        eventosDelMes.forEach(em ->
                System.out.println(
                        "Día: " + em.getDia() + " | " +
                        "Título: " + em.getTitulo() + " | " +
                        "Icono: " + em.getIcono() + " | " +
                        "Color: " + em.getColor() + "\n"
                )
        );
    }

    @Test
    void consultarInscripciones() {
        List<UsuarioInscrito> usuarioInscritos = eventoRepository.inscripciones(8);
        List<UsuarioInscrito> listaDeEspera = eventoRepository.listaDeEspera(8);

        System.out.println("Usuarios inscritos:");

        usuarioInscritos.forEach(ui ->
                System.out.println(
                        "Nombre: " + ui.getUsuario().getNombre() + "  " + ui.getUsuario().getApellidos() + "\n"
                )
        );

        System.out.println("Lista de espera:");

        listaDeEspera.forEach(le ->
                System.out.println(
                        "Nombre: " + le.getUsuario().getNombre() + "  " + le.getUsuario().getApellidos() + "\n"
                )
        );
    }
}