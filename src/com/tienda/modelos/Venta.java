package com.tienda.modelos;

public class Venta {
    private Producto producto;
    private Vendedor vendedor;
    private int cantidad;

    public Venta(Producto producto, Vendedor vendedor, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.producto = producto;
        this.vendedor = vendedor;
        this.cantidad = cantidad;
    }
 // Calcula el monto total de esta venta (sin decidir comisión)
    public double calcularTotal() {
        return producto.getPrecio() * cantidad;
    }

    public Producto getProducto() { return producto; }
    public Vendedor getVendedor() { return vendedor; }
    public int getCantidad() { return cantidad; }

    @Override
    public String toString() {
        return "Venta: " + cantidad + "x " + producto.getNombre() +
               " | Vendedor: " + vendedor.getNombre() +
               " | Total: $" + calcularTotal();
    }
}