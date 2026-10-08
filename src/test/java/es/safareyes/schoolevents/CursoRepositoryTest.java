package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Curso;
import es.safareyes.schoolevents.repositorios.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CursoRepositoryTest {
    @Autowired
    private CursoRepository cursoRepository;

    @Test
    void consultarCursos() {
        List<Curso> cursos = cursoRepository.findAll();

        cursos.forEach(curso -> System.out.println(curso.getNombre()));
    }
}