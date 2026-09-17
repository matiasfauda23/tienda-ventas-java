package com.tienda.servicios;

import com.tienda.excepciones.ElementoNoEncontradoException;
import com.tienda.modelos.Producto;
import com.tienda.modelos.Vendedor;
import com.tienda.modelos.Venta;

import java.util.ArrayList;
import java.util.List;

public class TiendaService {
    // Almacenamiento en memoria usando colecciones
    private List<Producto> productos = new ArrayList<>();
    private List<Vendedor> vendedores = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();

    // Metodos de registro
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void agregarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void registrarVenta(String codigoProducto, String codigoVendedor, int cantidad) throws ElementoNoEncontradoException {
        Producto p = buscarProductoPorCodigo(codigoProducto);
        Vendedor v = buscarVendedorPorCodigo(codigoVendedor);

        Venta nuevaVenta = new Venta(p, v, cantidad);
        ventas.add(nuevaVenta);
    }

    // Metodos de busqueda
    public Producto buscarProductoPorCodigo(String codigo) throws ElementoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró el producto con código: " + codigo);
    }

    public Vendedor buscarVendedorPorCodigo(String codigo) throws ElementoNoEncontradoException {
        for (Vendedor v : vendedores) {
            if (v.getCodigo().equalsIgnoreCase(codigo)) {
                return v;
            }
        }
        throw new ElementoNoEncontradoException("No se encontró el vendedor con código: " + codigo);
    }

    // Buscador por categoría 
    public List<Producto> buscarProductosPorCategoria(String categoria) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                filtrados.add(p);
            }
        }
        return filtrados;
    }

    // Calculo de comisiones
    public double calcularComisionTotalVendedor(String codigoVendedor) throws ElementoNoEncontradoException {
        Vendedor v = buscarVendedorPorCodigo(codigoVendedor);
        double totalComision = 0.0;

        for (Venta venta : ventas) {
            if (venta.getVendedor().getCodigo().equalsIgnoreCase(v.getCodigo())) {
                totalComision += venta.calcularComision();
            }
        }
        return totalComision;
    }

    // Getters para listar información en consola
    public List<Producto> getProductos() { return productos; }
    public List<Vendedor> getVendedores() { return vendedores; }
    public List<Venta> getVentas() { return ventas; }
}