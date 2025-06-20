package com.ejemplo.inventario.repositorio;

import com.ejemplo.inventario.modelo.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
