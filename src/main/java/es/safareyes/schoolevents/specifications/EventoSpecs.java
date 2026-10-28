package es.safareyes.schoolevents.specifications;

import es.safareyes.schoolevents.modelos.Evento;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;

public final class EventoSpecs {
    private EventoSpecs() {}

    public static Specification<Evento> tituloContiene(String titulo) {
        return (root, query, cb) -> {
            if (titulo == null || titulo.isBlank()) return null;
            return cb.like(cb.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
        };
    }

    public static Specification<Evento> cursosIn(List<Integer> cursos) {
        return (root, query, cb) -> {
          if (cursos.isEmpty()) return null;
          return root.join("cursos", JoinType.LEFT).get("id").in(cursos);
        };
    }

    public static Specification<Evento> categoriaEquals(Integer categoria) {
        return (root, query, cb) -> {
            if (categoria == null) return null;
            return cb.equal(root.get("categoria").get("id"), categoria);
        };
    }

    public static Specification<Evento> entreFechaHoraIniYFechaHoraFin(LocalDateTime fechaHoraIni, LocalDateTime fechaHoraFin) {
        return (root, query, cb) -> {
            if (fechaHoraIni == null) return null;
            return cb.between(root.get("fechaHoraIni"), fechaHoraIni, fechaHoraFin == null ? fechaHoraIni : fechaHoraFin);
        };
    }
}