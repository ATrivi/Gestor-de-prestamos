package com.example.Gestordeprestamos.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name ="usuario")
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Column(unique = true)
    private String nombreUsuario;
    @NotBlank
    private String password;
    @NotBlank
    private String rol;
    @NotBlank
    private String email;

    public Usuario() {}

    public Usuario(int idUsuario, String nombreUsuario, String password, String rol, String email) {
        this.id = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.email = email;
    }

    public int getIdUsuario() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int idUsuario) {
        this.id = idUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
