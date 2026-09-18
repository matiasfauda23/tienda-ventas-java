package com.tienda.excepciones;

public class CodigoDuplicadoException extends Exception {
	// Identificador único para serialización
    private static final long serialVersionUID = 1L;
    
    public CodigoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}