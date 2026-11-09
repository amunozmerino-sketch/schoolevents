package es.safareyes.schoolevents.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface FechaMapper {
    default String fechaAString(LocalDateTime fecha) {
        return fecha.toLocalDate().toString();
    }

    default String horaAString(LocalDateTime fecha) {
        return fecha.toLocalTime().toString();
    }

    @Named("fechaCompletaAString")
    default String fechaHoraAString(LocalDateTime fecha) {
        return fecha.toString();
    }

    default LocalDateTime stringAFecha(String fechaString) {
        return LocalDateTime.parse(fechaString);
    }
}