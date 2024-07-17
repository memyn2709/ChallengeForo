package com.forohub.domain.dto;

import com.forohub.domain.model.roles.Rol;

import java.util.List;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        List<Rol> roles
) {
}
