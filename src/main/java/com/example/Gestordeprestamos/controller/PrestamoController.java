package com.example.Gestordeprestamos.controller;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.repository.PrestamoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    private PrestamoRepository prestamoRepository;

    public PrestamoController(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();

    }

    @PostMapping
    public Prestamo crearPrestamo (@Valid @RequestBody Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
        if (!prestamoRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si no existe
        }

        prestamoRepository.deleteById(id); // Borra el préstamo
        return ResponseEntity.noContent().build();   // Devuelve 204 No Content si se borró
    }

    @GetMapping("/estado")
    public List<Prestamo> buscarPorEstado(@RequestParam boolean devuelto) {
        return prestamoRepository.findByDevuelto(devuelto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(
            @PathVariable Integer id,
            @Valid @RequestBody Prestamo nuevoPrestamo) {

        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(id);

        if (prestamoOptional.isPresent()) {
            Prestamo existente = prestamoOptional.get();

            existente.setPrestador(nuevoPrestamo.getPrestador());
            existente.setPrestatario(nuevoPrestamo.getPrestatario());
            existente.setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            existente.setFechaDevolucion(nuevoPrestamo.getFechaDevolucion());
            existente.setDevuelto(nuevoPrestamo.isDevuelto());
            existente.setCategoria(nuevoPrestamo.getCategoria());

            Prestamo actualizado = prestamoRepository.save(existente);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
