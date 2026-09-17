package com.tienda.modelos;

public class Venta {
    private Producto producto;
    private Vendedor vendedor;
    private int cantidad;

    public Venta(Producto producto, Vendedor vendedor, int cantidad) {
        this.producto = producto;
        this.vendedor = vendedor;
        this.cantidad = cantidad;
    }

    // Método que calcula la comisión 
    public double calcularComision() {
        double totalVenta = producto.getPrecio() * cantidad;
        
        if (cantidad <= 2) {
            return totalVenta * 0.05; 
        } else {
            return totalVenta * 0.10; 
        }
    }

    public Producto getProducto() { return producto; }
    public Vendedor getVendedor() { return vendedor; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "Venta: " + cantidad + "x " + producto.getNombre() + 
               " | Vendedor: " + vendedor.getNombre() + 
               " | Comisión ganada: $" + calcularComision();
    }
}