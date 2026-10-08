package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DetallesEvento {
    private String lugar;
    private String[] requisitos;
    private Integer plazasLibres;
    private String[] cursos;
}