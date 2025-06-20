package com.ejemplo.inventario.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// Entidad Producto con validaciones y relación ManyToOne a Marca
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // No puede ser vacío ni nulo
    private String nombre;

    @NotBlank // No puede ser vacío ni nulo
    private String descripcion;

    @Min(1) // Precio mínimo permitido: 1
    private double precio;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Producto() {}

    // Constructor con parámetros incluyendo marca
    public Producto(String nombre, String descripcion, double precio, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.marca = marca;
    }

    // Getters para todos los atributos
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public Marca getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Producto[id=" + id + ", nombre='" + nombre + "', precio=" + precio + "]";
    }
}
