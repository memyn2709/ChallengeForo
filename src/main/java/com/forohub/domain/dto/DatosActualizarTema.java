package com.forohub.domain.dto;

import com.forohub.domain.model.tema.Estado;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTema(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        //@Pattern(regexp = "^(ABIERTO|CERRADO)$", message = "El estado debe ser ABIERTO o CERRADO")
        Estado status
) {
}
