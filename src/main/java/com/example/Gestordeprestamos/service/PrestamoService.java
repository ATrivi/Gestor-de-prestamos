package com.example.Gestordeprestamos.service;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

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

    public Prestamo crearPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public boolean eliminarPrestamo(Integer id) {
        if (!prestamoRepository.existsById(id)) {
            return false;
        }

        prestamoRepository.deleteById(id);
        return true;
    }
    public List<Prestamo> listarTodos(){
        return prestamoRepository.findAll();
    }

    public List<Prestamo> buscarPorEstado(boolean devuelto){
        return prestamoRepository.findByDevuelto(devuelto);
    }

    public List<Prestamo> buscarPorPrestatario(String nombrePrestatario){
        return prestamoRepository.findByPrestatarioIgnoreCase(nombrePrestatario);
    }

    public List<Prestamo> buscarPorCategoria(String nombreCat){
        return prestamoRepository.findByCategoriaIgnoreCase(nombreCat);
    }


}

