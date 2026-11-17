package es.safareyes.schoolevents.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class EventoDetalleResponse {
    Integer id;

    String titulo;

    String foto;

    String descripcion;

    String lugar;

    String fechaIni;

    String horaIni;

    String horaFin;

    String fechaLimite;

    Integer aforoActual;

    Integer aforoMax;

    Integer enEspera;

    List<CursoResponse> cursos;

    CategoriaResponse categoria;

    List<RequisitoResumenResponse> requisitos;
}