package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dto.response.PublicacionConFotosResponse;
import es.safareyes.schoolevents.modelos.Publicacion;
import es.safareyes.schoolevents.repositorios.PublicacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PublicacionRepositoryTest {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Test
    void consultarPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.buscarPublicacionesPorEvento(4);

//        publicaciones.forEach(p -> System.out.println(
//                    "Usuario: " + p.getUsuario() + " | " +
//                    "Rol: " + p.getRol() + " | " +
//                    "Texto: " + p.getTexto() + " | " +
//                    "Fecha y hora: " + p.getFechaHora() + " | " +
//                    "Fotos: " + Arrays.toString(p.getFotos()) + "\n"
//                )
//        );
    }
}