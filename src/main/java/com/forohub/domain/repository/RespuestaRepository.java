package com.forohub.domain.repository;

import com.forohub.domain.model.respuesta.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query(value = "SELECT * FROM respuesta WHERE tema_id = :temaId", nativeQuery = true)
    List<Respuesta> findAllByTemaId(Long temaId);
}

