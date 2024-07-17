package com.forohub.domain.service;

import com.forohub.domain.dto.DatosLoginUsuario;
import com.forohub.domain.dto.DatosRegistroUsuario;
import com.forohub.domain.dto.DatosRespuestaRegistroUsuario;
import com.forohub.domain.dto.JWTtoken;
import com.forohub.domain.model.roles.Rol;
import com.forohub.domain.model.usuario.Usuario;
import com.forohub.domain.repository.RolRepository;
import com.forohub.domain.repository.UsuarioRepository;
import com.forohub.infra.errores.ValidacionDeIntegridad;
import com.forohub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public RegistroService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public DatosRespuestaRegistroUsuario regitrarUsuario(DatosRegistroUsuario datosUsuario) {

        if(usuarioRepository.findByCorreoElectronico(datosUsuario.CorreoElectronico()).isPresent()){
            throw new ValidacionDeIntegridad("El correo ya esta registrado");
        }

        var passwordEncriptada = passwordEncoder.encode(datosUsuario.clave());
        Rol rol = rolRepository.findByNombre("USER");
        Usuario usuario = usuarioRepository.save(new Usuario(datosUsuario, passwordEncriptada, rol));
        return new DatosRespuestaRegistroUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }

    public JWTtoken loginUsuario(DatosLoginUsuario datos){

        if(usuarioRepository.findByCorreoElectronico(datos.correoElectronico()).isEmpty() ||
                !passwordEncoder.matches(datos.clave(),
                        usuarioRepository.findByCorreoElectronico(datos.correoElectronico()).get().getPassword()))
        {
            throw new ValidacionDeIntegridad("usuario y/o clave incorrectos");
        }


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(),
                datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generateToken((Usuario) usuarioAutenticado.getPrincipal());
        return new JWTtoken(JWTtoken);
    }
}
