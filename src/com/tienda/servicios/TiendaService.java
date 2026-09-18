package com.tienda.servicios;

import com.tienda.excepciones.CodigoDuplicadoException;
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
    public void agregarProducto(Producto producto) throws CodigoDuplicadoException {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(producto.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe un producto con código: " + producto.getCodigo());
            }
        }
        productos.add(producto);
    }

    public void agregarVendedor(Vendedor vendedor) throws CodigoDuplicadoException {
        for (Vendedor v : vendedores) {
            if (v.getCodigo().equalsIgnoreCase(vendedor.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe un vendedor con código: " + vendedor.getCodigo());
            }
        }
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
    
    public List<Producto> buscarProductosPorNombre(String texto) {
        List<Producto> filtrados = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(p);
            }
        }
        return filtrados;
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

    public double calcularComisionTotalVendedor(String codigoVendedor) throws ElementoNoEncontradoException {
        Vendedor v = buscarVendedorPorCodigo(codigoVendedor);

        int cantidadVentas = 0;
        double totalVendido = 0.0;

        for (Venta venta : ventas) {
            if (venta.getVendedor().getCodigo().equalsIgnoreCase(v.getCodigo())) {
                cantidadVentas++;
                totalVendido += venta.calcularTotal();
            }
        }

        double porcentaje = (cantidadVentas <= 2) ? 0.05 : 0.10;

        return totalVendido * porcentaje;
    }

    // Getters para listar información en consola
    public List<Producto> getProductos() { return productos; }
    public List<Vendedor> getVendedores() { return vendedores; }
    public List<Venta> getVentas() { return ventas; }
}