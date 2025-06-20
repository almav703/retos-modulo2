package com.ejemplo.inventario.config;

import com.ejemplo.inventario.modelo.Marca;
import com.ejemplo.inventario.modelo.Producto;
import com.ejemplo.inventario.repositorio.MarcaRepository;
import com.ejemplo.inventario.repositorio.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuración para cargar datos iniciales al iniciar la aplicación
@Configuration
public class CargaInicial {

    // Bean que ejecuta la carga de datos y muestra productos agrupados por marca
    @Bean
    public CommandLineRunner cargarDatos(MarcaRepository marcaRepo, ProductoRepository productoRepo) {
        return args -> {
            // Crear y guardar marcas
            Marca dell = new Marca("Dell");
            Marca hp = new Marca("HP");
            marcaRepo.save(dell);
            marcaRepo.save(hp);

            // Crear y guardar productos asociados a marcas
            productoRepo.save(new Producto("Monitor Dell 24", "Monitor FHD", 3200.00, dell));
            productoRepo.save(new Producto("Laptop Dell XPS", "Ultrabook", 18000.00, dell));
            productoRepo.save(new Producto("Impresora HP Laser", "Impresora monocromática", 2800.00, hp));
            productoRepo.save(new Producto("Mouse HP", "Mouse óptico", 350.00, hp));

            // Imprimir productos agrupados por marca en consola
            System.out.println("Productos agrupados por marca:");
            marcaRepo.findAll().forEach(marca -> {
                System.out.println(marca.getNombre() + ":");
                productoRepo.findAll().stream()
                        .filter(p -> p.getMarca().getId().equals(marca.getId()))
                        .forEach(p -> System.out.println("   - " + p.getNombre()));
            });
        };
    }
}
