package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Categoria;
import es.safareyes.schoolevents.repositorios.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void consultarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();

        categorias.forEach(c -> System.out.println(
                "Nombre: " + c.getNombre() + " | " + "Icono: " + c.getIcono() + " | " + "Color: " + c.getColor() + "\n"
                )
        );
    }
}