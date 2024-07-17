package com.forohub.domain.dto;

import com.forohub.domain.model.respuesta.Respuesta;

import java.util.List;

public record DatosListaRespuestaPorTema(
        List<Respuesta> respuestas
) {
}
