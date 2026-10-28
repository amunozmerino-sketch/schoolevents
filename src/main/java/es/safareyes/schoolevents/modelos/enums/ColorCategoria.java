package es.safareyes.schoolevents.modelos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorCategoria {
    CIENCIA("#33A9FE"),
    DEPORTE("#FF6200"),
    SOLIDARIO("#16B900"),
    OCIO("#FED533"),
    CULTURA("#FF6DE4"),
    ACADEMICO("#8E33FE");

    private final String codHex;
}