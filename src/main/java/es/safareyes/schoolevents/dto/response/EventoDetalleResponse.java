package es.safareyes.schoolevents.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class EventoDetalleResponse {
    Integer id;

    String titulo;

    String descripcion;

    String lugar;

    String fechaIni;

    String horaIni;

    String horaFin;

    String fechaLimite;

    Integer aforoMax;

    Integer enEspera;

    List<CursoResumenResponse> cursos;

    CategoriaResumenResponse categoria;

    List<RequisitoResumenResponse> requisitos;
}