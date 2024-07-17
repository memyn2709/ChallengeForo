package com.forohub.domain.repository;

import com.forohub.domain.model.roles.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByNombre(String usuario);
}
