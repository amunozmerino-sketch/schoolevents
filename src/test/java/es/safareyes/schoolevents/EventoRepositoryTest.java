package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dto.DetallesEvento;
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
        List<DetallesEvento> detallesEventos = eventoRepository.detallesEvento(1);

        detallesEventos.forEach(de ->
                System.out.println(
                        de.getLugar() + "\n" +
                        Arrays.toString(de.getCursos()) + "\n" +
                        de.getPlazasLibres() + "\n" +
                        Arrays.toString(de.getRequisitos())
                )
        );
    }
}