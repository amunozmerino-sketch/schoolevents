package es.safareyes.schoolevents.modelos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@RequiredArgsConstructor
public enum ColorCategoria {
    CIENCIA("#33A9FE"),
    DEPORTE("#FF6200"),
    SOLIDARIO("#16B900"),
    OCIO("#FED533"),
    CULTURA("#FF6DE4"),
    ACADEMICO("#8E33FE"),
    DESCONOCIDO("#9E9E9E");

    private final String codHex;

    public static ColorCategoria posicionAColor(Integer pos) {
        if (pos < 0 || pos > values().length) {
            log.warn("Color de categoría no válido en la BD: {}", pos);
            return DESCONOCIDO;
        }
        return values()[pos];
    }
}