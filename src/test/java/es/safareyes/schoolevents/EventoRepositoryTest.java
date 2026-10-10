package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dto.DetallesEvento;
import es.safareyes.schoolevents.dto.EventosDelMes;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.repositorios.EventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.time.LocalDateTime;
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
                    "Requisitos: " + Arrays.toString(detallesEvento.getRequisitos()
                )
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
    void consultarEventosFiltrados() {
        List<Integer> cursos = List.of(6, 7, 10, 12, 13);
        List<Integer> categorias = List.of(1, 2, 3, 4, 5, 6);
        LocalDateTime desde = LocalDateTime.of(2026,9,1,0,0,0);
        LocalDateTime hasta = LocalDateTime.of(2026,12,31,23,59,59);
        List<Evento> eventosFiltrados = eventoRepository.buscarEventosFiltrados(cursos, categorias, null, desde, hasta);

        eventosFiltrados.forEach(ef -> System.out.println(
                    "Título: " + ef.getTitulo()
                )
        );
    }
}