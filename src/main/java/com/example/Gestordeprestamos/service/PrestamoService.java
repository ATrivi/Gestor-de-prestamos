package com.example.Gestordeprestamos.service;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.repository.PrestamoRepository;
import com.example.Gestordeprestamos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.Gestordeprestamos.model.Usuario;

import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    private PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoService (UsuarioRepository usuarioRepository, PrestamoRepository prestamoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.prestamoRepository = prestamoRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Optional<Prestamo> actualizarPrestamo(Integer id, Prestamo nuevoPrestamo) {
        Optional<Prestamo> existente = prestamoRepository.findById(id);


        if (existente.isPresent()) {
            Prestamo prestamo = existente.get();

            prestamo.setPrestador(nuevoPrestamo.getPrestador());
            prestamo.setPrestatario(nuevoPrestamo.getPrestatario());
            prestamo.setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            prestamo.setFechaDevolucion(nuevoPrestamo.getFechaDevolucion());
            prestamo.setDevuelto(nuevoPrestamo.isDevuelto());
            prestamo.setCategoria(nuevoPrestamo.getCategoria());

            Prestamo actualizado = prestamoRepository.save(prestamo);
            return Optional.of(actualizado);
        }

        return Optional.empty();
    }

    @PreAuthorize("isAuthenticated()")
    public Prestamo crearPrestamo(Prestamo prestamo) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();

        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        prestamo.setUsuario(usuario);
        return prestamoRepository.save(prestamo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public boolean eliminarPrestamo(Integer id) {
        if (!prestamoRepository.existsById(id)) {
            return false;
        }

        prestamoRepository.deleteById(id);
        return true;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Prestamo> listarTodos(){
        return prestamoRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Prestamo> buscarPorEstado(boolean devuelto){
        return prestamoRepository.findByDevuelto(devuelto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Prestamo> buscarPorPrestatario(String nombrePrestatario){
        return prestamoRepository.findByPrestatarioIgnoreCase(nombrePrestatario);
    }
    @PreAuthorize("isAuthenticated()")
    public List<Prestamo> buscarPorUsuario(String nombreUsuario){
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return prestamoRepository.findByUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Prestamo> buscarPorCategoria(String nombreCat){
        return prestamoRepository.findByCategoriaIgnoreCase(nombreCat);
    }

    @PreAuthorize("isAuthenticated()")
    public List<Prestamo> buscarMisPrestamosVencidos() {
        // 1) Usuario actual (según Spring Security)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();

        // 2) Fecha de corte calculada en el servidor (confiable)
        LocalDate hoy = LocalDate.now();

        // 3) Consulta
        return prestamoRepository.findVencidosDeUsuario(hoy, nombreUsuario);
    }


}

