package com.example.Gestordeprestamos.repository;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository <Prestamo, Integer> {

    List<Prestamo> findByDevuelto(boolean devuelto);
    List<Prestamo> findByPrestatarioIgnoreCase (String prestatario);
    List<Prestamo> findByCategoriaIgnoreCase (String categoria);
    List<Prestamo> findByUsuario (Usuario usuario);

    @Query("""
       SELECT p 
       FROM Prestamo p
       WHERE p.devuelto = false
         AND p.fechaDevolucion < :fecha
         AND (
              LOWER(p.prestador) = LOWER(:nombre)
              OR LOWER(p.prestatario) = LOWER(:nombre)
         )
       """)
    List<Prestamo> findVencidosDeUsuario(@Param("fecha") LocalDate fecha,
                                         @Param("nombre") String nombre);



}
