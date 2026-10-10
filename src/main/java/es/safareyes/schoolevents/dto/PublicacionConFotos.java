package es.safareyes.schoolevents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class PublicacionConFotos {
    private String usuario;
    private Integer rol;
    private String texto;
    private LocalDateTime fechaHora;
    private String[] fotos;
}