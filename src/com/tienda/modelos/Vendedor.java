package com.tienda.modelos;

public class Vendedor {
    private String codigo;
    private String nombre;
    private double sueldo;

    public Vendedor(String codigo, String nombre, double sueldo) {
        if (sueldo < 0) {
            throw new IllegalArgumentException("El sueldo no puede ser negativo");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSueldo() { return sueldo; }
    public void setSueldo(double sueldo) { this.sueldo = sueldo; }

    @Override
    public String toString() {
        return "Vendedor [" + codigo + "] " + nombre + " - Sueldo Base: $" + sueldo;
    }
}