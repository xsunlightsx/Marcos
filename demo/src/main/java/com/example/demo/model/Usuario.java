package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "contraseña", nullable = false, length = 100)
    private String password; // Lo nombramos 'password' en Java, aunque la columna es 'contraseña'

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol; // Usaremos un enum para el rol

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // --- Enum para el Rol ---
    public enum Rol {
        admin, cliente
    }

    // --- Constructor vacío ---
    public Usuario() {
    }

    // --- Constructor con todos los campos (opcional) ---
    public Usuario(String nombreUsuario, String password, Rol rol, String email) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.rol = rol;
        this.email = email;
    }

    // --- Getters y Setters ---

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}