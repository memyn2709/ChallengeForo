package com.forohub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String mensaje){
        super(mensaje);
    }
}
