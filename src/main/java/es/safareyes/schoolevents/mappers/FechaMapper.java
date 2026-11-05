package es.safareyes.schoolevents.mappers;

import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Mapper(componentModel = "spring")
public interface FechaMapper {
    default String fechaAString(LocalDateTime fecha) {
        return String.format("%d/%d/%d", fecha.getDayOfMonth(), fecha.getMonthValue(), fecha.getYear());
    }

    default String horaAString(LocalDateTime fecha) {
        return String.format("%d:%d", fecha.getHour(), fecha.getMinute());
    }

    default LocalDateTime stringAFecha(String fechaString) {
        String[] fechaHoraSeparada = fechaString.split("T");
        String[] fechaSeparada = fechaHoraSeparada[0].split("-");
        String[] horaSeparada = fechaHoraSeparada[1].split(":");

        LocalDate fecha = LocalDate.of(Integer.parseInt(fechaSeparada[0]), Integer.parseInt(fechaSeparada[1]), Integer.parseInt(fechaSeparada[2]));
        LocalTime hora = LocalTime.of(Integer.parseInt(horaSeparada[0]), Integer.parseInt(horaSeparada[1]));

        return LocalDateTime.of(fecha, hora);
    }
}