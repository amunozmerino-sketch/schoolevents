package es.safareyes.schoolevents.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter @Setter
public class EventoResumenResponse {
    Integer id;

    String titulo;

    String foto;

    Integer aforoActual;

    Integer aforoMax;

    Integer enEspera;

    String colorCategoria;

    String fechaIni;

    String fechaLimite;

    String horaIni;

    String horaFin;
}