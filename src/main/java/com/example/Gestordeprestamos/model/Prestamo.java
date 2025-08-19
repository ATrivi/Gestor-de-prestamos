package com.example.Gestordeprestamos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="Prestamo")
public class Prestamo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idPrestamo;
    @NotBlank
    private String prestador;
    @NotBlank
    private String prestatario;
    @NotNull
    private LocalDate fechaPrestamo;
    @NotNull
    private LocalDate fechaDevolucion;
    @NotNull
    private Boolean devuelto;
    @NotBlank
    private String categoria;

    public Prestamo() {
    }

    public Prestamo(String categoria, boolean devuelto, LocalDate fechaDevolucion, String prestador, String prestatario, LocalDate fechaPrestamo) {

        this.categoria = categoria;
        this.devuelto = devuelto;
        this.fechaDevolucion = fechaDevolucion;
        this.prestador = prestador;
        this.prestatario = prestatario;
        this.fechaPrestamo = fechaPrestamo;

    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getPrestatario() {
        return prestatario;
    }

    public void setPrestatario(String prestatario) {
        this.prestatario = prestatario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

