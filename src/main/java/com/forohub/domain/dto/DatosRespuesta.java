package com.forohub.domain.dto;

import java.time.LocalDateTime;

public record DatosRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fecha,
        String temaTitulo,
        String autor
) {
}
