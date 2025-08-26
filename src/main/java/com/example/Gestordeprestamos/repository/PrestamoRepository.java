package com.example.Gestordeprestamos.repository;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository <Prestamo, Integer> {

    List<Prestamo> findByDevuelto(boolean devuelto);
    List<Prestamo> findByPrestatarioIgnoreCase (String prestatario);
    List<Prestamo> findByCategoriaIgnoreCase (String categoria);
    List<Prestamo> findByUsuario (Usuario usuario);



}
