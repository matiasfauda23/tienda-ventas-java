package com.tienda.excepciones;

// Heredamos de Exception para que sea una excepción verificada
public class ElementoNoEncontradoException extends Exception {
	// Identificador único para serialización
    private static final long serialVersionUID = 1L;
    
    public ElementoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}