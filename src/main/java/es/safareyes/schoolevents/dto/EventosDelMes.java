package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EventosDelMes {
    private BigDecimal dia;
    private String titulo;
    private String icono;
    private Integer color;
}