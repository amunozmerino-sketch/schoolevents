package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class InformeParticipacion {
    private String categoria; // Usar alias
    private String curso; // Usar alias
    private Long participacion; // Usar alias
}
