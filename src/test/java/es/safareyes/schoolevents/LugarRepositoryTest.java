package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Lugar;
import es.safareyes.schoolevents.repositorios.LugarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LugarRepositoryTest {
    @Autowired
    private LugarRepository lugarRepository;

    @Test
    void consultarLugares() {
        List<Lugar> lugares = lugarRepository.findAll();

        lugares.forEach(l -> System.out.println(
                    "Nombre: " + l.getNombre() + " | " + "Aforo máximo: " + l.getAforoMax() + "\n"
                )
        );
    }
}