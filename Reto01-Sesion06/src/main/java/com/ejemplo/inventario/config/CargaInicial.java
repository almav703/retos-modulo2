package com.ejemplo.inventario.config;

import com.ejemplo.inventario.modelo.Producto;
import com.ejemplo.inventario.repositorio.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargaInicial {

    @Bean
    public CommandLineRunner ejecutar(ProductoRepository productoRepo) {
        return args -> {
            // Crear y guardar productos
            productoRepo.save(new Producto("Proyector LED", "Proyector portátil para presentaciones", 1800.00));
            productoRepo.save(new Producto("Micrófono USB", "Micrófono profesional con cancelación de ruido", 950.00));
            productoRepo.save(new Producto("Hub USB-C", "Adaptador múltiple para laptops", 600.00));
            productoRepo.save(new Producto("Pantalla táctil", "Monitor interactivo de 24 pulgadas", 4300.00));

            // Mostrar productos con precio mayor a 1500
            System.out.println("Productos con precio mayor a 1500:");
            productoRepo.findByPrecioGreaterThan(1500).forEach(System.out::println);

            // Mostrar productos que contienen 'usb'
            System.out.println("\nProductos que contienen 'usb':");
            productoRepo.findByNombreContainingIgnoreCase("usb").forEach(System.out::println);

            // Mostrar productos con precio entre 500 y 2000
            System.out.println("\nProductos con precio entre 500 y 2000:");
            productoRepo.findByPrecioBetween(500, 2000).forEach(System.out::println);

            // Mostrar productos cuyo nombre inicia con 'p'
            System.out.println("\nProductos cuyo nombre inicia con 'm':");
            productoRepo.findByNombreStartingWithIgnoreCase("m").forEach(System.out::println);
        };
    }
}
