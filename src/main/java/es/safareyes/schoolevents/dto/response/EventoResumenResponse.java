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

    Integer aforoActual;

    Integer aforoMax;

    Integer enEspera;

    String colorCategoria;

    LocalDateTime fechaHoraIni;

    LocalDateTime fechaHoraFin;

    LocalDateTime fechaHoraLimite;
}