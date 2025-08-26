package com.example.Gestordeprestamos.controller;

import com.example.Gestordeprestamos.external.CountryInfo;
import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.service.PaisService;
import com.example.Gestordeprestamos.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {


    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @Autowired
    private PaisService paisService;

    @GetMapping("/pais")
    public CountryInfo getInfoDelPais(@RequestParam String nombre) {
        return paisService.getInfoPorNombre(nombre);
    }


    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.listarTodos();

    }
    @GetMapping("/mios")
    public List<Prestamo> listarMisPrestamos() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = auth.getName();

        return prestamoService.buscarPorUsuario(nombreUsuario);
    }

    @PostMapping
    public Prestamo crearPrestamo (@Valid @RequestBody Prestamo prestamo) {
        return prestamoService.crearPrestamo(prestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Integer id) {
            boolean eliminado = prestamoService.eliminarPrestamo(id); // Devuelve 404 si no existe
        if(eliminado) {
            return ResponseEntity.noContent().build(); //204 si se borró
        } else {
            return ResponseEntity.notFound().build(); //404 si no existía
        }
    }

    @GetMapping("/estado")
    public List<Prestamo> buscarPorEstado(@RequestParam boolean devuelto) {
        return prestamoService.buscarPorEstado(devuelto);
    }

    @GetMapping("/prestatario")
    public List<Prestamo> buscarPorPrestatario(@RequestParam String nombrePrestatario) {
        return prestamoService.buscarPorPrestatario(nombrePrestatario);
    }

    @GetMapping("/categoria")
    public List<Prestamo> buscarPorCategoria(@RequestParam String nombreCat) {
        return prestamoService.buscarPorCategoria(nombreCat);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(
            @PathVariable Integer id,
            @Valid @RequestBody Prestamo nuevoPrestamo) {

        return prestamoService.actualizarPrestamo(id, nuevoPrestamo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        }
    }

