package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EventosDelMes {
    private Integer dia;
    private String titulo;
    private String icono;
    private Integer color;
}