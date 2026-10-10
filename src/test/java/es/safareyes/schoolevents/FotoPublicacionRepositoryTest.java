package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.FotoPublicacion;
import es.safareyes.schoolevents.repositorios.FotoPublicacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FotoPublicacionRepositoryTest {
    @Autowired
    private FotoPublicacionRepository fotoPublicacionRepository;

    @Test
    void consultarFoto() {
        Integer id = 1;
        Optional<FotoPublicacion> fotoPublicacion = fotoPublicacionRepository.findById(id);

        System.out.println(fotoPublicacion.orElseThrow(() -> new NoSuchElementException("No existe la imagen " + id)).getEnlace());
    }
}