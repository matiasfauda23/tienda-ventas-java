package com.tienda.app;

import com.tienda.excepciones.CodigoDuplicadoException;
import com.tienda.excepciones.ElementoNoEncontradoException;
import com.tienda.modelos.Producto;
import com.tienda.modelos.Vendedor;
import com.tienda.modelos.Venta;
import com.tienda.servicios.TiendaService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static TiendaService tiendaService = new TiendaService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDatosPrueba();

        int opcion = -1;
        do {
            mostrarMenu();
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        registrarProducto();
                        break;
                    case 2:
                        registrarVendedor();
                        break;
                    case 3:
                        registrarVenta();
                        break;
                    case 4:
                        buscarPorCategoria();
                        break;
                    case 5:
                        calcularComisionVendedor();
                        break;
                    case 6:
                        listarTodo();
                        break;
                    case 7:
                        buscarPorNombre();
                        break;
                    case 0:
                        System.out.println("\n¡Gracias por usar el sistema de ventas!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }

            System.out.println();
        } while (opcion != 0);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== SISTEMA DE GESTIÓN DE TIENDA ===");
        System.out.println("1. Registrar Producto");
        System.out.println("2. Registrar Vendedor");
        System.out.println("3. Registrar Venta");
        System.out.println("4. Buscar Productos por Categoría");
        System.out.println("5. Calcular Comisión de Vendedor");
        System.out.println("6. Listar Productos, Vendedores y Ventas");
        System.out.println("7. Buscar Productos por Nombre");
        System.out.println("0. Salir");
    }

    private static void cargarDatosPrueba() {
        try {
            tiendaService.agregarProducto(new Producto("P001", "Notebook", 1500.0, "Tecnologia"));
            tiendaService.agregarProducto(new Producto("P002", "Mouse Gamer", 30.0, "Tecnologia"));
            tiendaService.agregarProducto(new Producto("P003", "Silla Ergonómica", 200.0, "Muebles"));

            tiendaService.agregarVendedor(new Vendedor("V001", "Carlos Gómez", 800.0));
            tiendaService.agregarVendedor(new Vendedor("V002", "Ana Martínez", 850.0));
        } catch (CodigoDuplicadoException e) {
            System.out.println("Error al cargar datos de prueba: " + e.getMessage());
        }
    }

    private static void registrarProducto() {
        System.out.println("\n--- REGISTRAR PRODUCTO ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = Double.parseDouble(scanner.nextLine());
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        Producto producto = new Producto(codigo, nombre, precio, categoria);
        try {
            tiendaService.agregarProducto(producto);
            System.out.println("¡Producto registrado con éxito!");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registrarVendedor() {
        System.out.println("\n--- REGISTRAR VENDEDOR ---");
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = Double.parseDouble(scanner.nextLine());

        Vendedor vendedor = new Vendedor(codigo, nombre, sueldo);
        try {
            tiendaService.agregarVendedor(vendedor);
            System.out.println("¡Vendedor registrado con éxito!");
        } catch (CodigoDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registrarVenta() {
        System.out.println("\n--- REGISTRAR VENTA ---");
        System.out.print("Código de Producto: ");
        String codProducto = scanner.nextLine();
        System.out.print("Código de Vendedor: ");
        String codVendedor = scanner.nextLine();
        System.out.print("Cantidad de unidades: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        try {
            tiendaService.registrarVenta(codProducto, codVendedor, cantidad);
            System.out.println("¡Venta registrada exitosamente!");
        } catch (ElementoNoEncontradoException e) {
            System.out.println("Error al registrar venta: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarPorCategoria() {
        System.out.println("\n--- BUSCAR PRODUCTOS POR CATEGORÍA ---");
        System.out.print("Ingrese la categoría: ");
        String categoria = scanner.nextLine();

        List<Producto> encontrados = tiendaService.buscarProductosPorCategoria(categoria);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos en la categoría: " + categoria);
        } else {
            System.out.println("Productos encontrados:");
            for (Producto p : encontrados) {
                System.out.println(" - " + p);
            }
        }
    }

    private static void buscarPorNombre() {
        System.out.println("\n--- BUSCAR PRODUCTOS POR NOMBRE ---");
        System.out.print("Ingrese el texto a buscar: ");
        String texto = scanner.nextLine();

        List<Producto> encontrados = tiendaService.buscarProductosPorNombre(texto);

        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron productos con ese nombre: " + texto);
        } else {
            System.out.println("Productos encontrados:");
            for (Producto p : encontrados) {
                System.out.println(" - " + p);
            }
        }
    }

    private static void calcularComisionVendedor() {
        System.out.println("\n--- CALCULAR COMISIÓN DE VENDEDOR ---");
        System.out.print("Código de Vendedor: ");
        String codigo = scanner.nextLine();

        try {
            Vendedor vendedor = tiendaService.buscarVendedorPorCodigo(codigo);
            double comision = tiendaService.calcularComisionTotalVendedor(codigo);
            System.out.println("Vendedor: " + vendedor.getNombre());
            System.out.println("Comisión total acumulada: $" + comision);
        } catch (ElementoNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listarTodo() {
        System.out.println("\n--- PRODUCTOS REGISTRADOS ---");
        for (Producto p : tiendaService.getProductos()) {
            System.out.println(p);
        }

        System.out.println("\n--- VENDEDORES REGISTRADOS ---");
        for (Vendedor v : tiendaService.getVendedores()) {
            System.out.println(v);
        }

        System.out.println("\n--- VENTAS REGISTRADAS ---");
        List<Venta> ventas = tiendaService.getVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
        } else {
            for (Venta v : ventas) {
                System.out.println(v);
            }
        }
    }
}