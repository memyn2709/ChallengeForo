package com.forohub.controller;

import com.forohub.domain.dto.DatosCrearRespuesta;
import com.forohub.domain.dto.DatosRespuesta;
import com.forohub.domain.model.respuesta.Respuesta;
import com.forohub.domain.model.tema.Tema;
import com.forohub.domain.model.usuario.Usuario;
import com.forohub.domain.repository.RespuestaRepository;
import com.forohub.domain.repository.TemaRepository;
import com.forohub.domain.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Hidden
@RestController
@RequestMapping("api/respuestas")


public class RespuestaController {

    private final
    UsuarioRepository usuarioRepository;

    private final
    TemaRepository temaRepository;

    private final
    RespuestaRepository respuestaRepository;

    @Autowired
    public RespuestaController(TemaRepository temaRepository, RespuestaRepository respuestaRepository,
                               UsuarioRepository usuarioRepository) {
        this.temaRepository = temaRepository;
        this.respuestaRepository = respuestaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DatosRespuesta> createRespuesta(@RequestBody DatosCrearRespuesta respuesta,
                                                          UriComponentsBuilder uriBuilder) {
        // TODO


        Tema tema = temaRepository.findById(respuesta.temaId())
                .orElseThrow(() -> new RuntimeException("Tema no encontrado"));

        Usuario usuario = usuarioRepository.findById(respuesta.autorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Respuesta resultadoRespuesta = respuestaRepository.save(new Respuesta(null, respuesta.mensaje(),
                usuario,  LocalDateTime.now(), tema, false));


        URI url = uriBuilder.path("/respuestas/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosRespuesta(resultadoRespuesta.getId(), resultadoRespuesta.getMensaje(),
                resultadoRespuesta.getFecha(), tema.getTitulo(), usuario.getNombre()));
    }


}
