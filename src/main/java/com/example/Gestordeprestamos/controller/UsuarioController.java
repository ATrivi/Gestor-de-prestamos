package com.example.Gestordeprestamos.controller;

import com.example.Gestordeprestamos.model.Prestamo;
import com.example.Gestordeprestamos.model.Usuario;
import com.example.Gestordeprestamos.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public String registrarUsuario (@Valid @RequestBody Usuario usuario) {

        if (usuarioRepository.findByNombreUsuario(usuario.getNombreUsuario()).isPresent()) {
            return "Ya existe un usuario con este nombre";
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);

        return "Usuario registrado correctamente";
    }
}
