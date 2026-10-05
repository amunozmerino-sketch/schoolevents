package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformeParticipacion {
    private String categoria; // Usar alias
    private String curso; // Usar alias
    private Long participacion; // Usar alias
}
