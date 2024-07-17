package com.forohub.domain.repository;

import com.forohub.domain.model.tema.Tema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    Page<Tema> findByActivoTrue(Pageable page);

    Optional<Tema> findByTitulo(String titulo);
}
