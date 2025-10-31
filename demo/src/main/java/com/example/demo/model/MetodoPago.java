package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long id; // Usamos Long para el ID

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre; // Nombre del método (e.g., "Tarjeta de Crédito", "Transferencia")

    @Column(name = "activo", nullable = false)
    private Boolean activo = true; // Si el método está disponible (valor por defecto)

    // Constructor por defecto (requerido por JPA)
    public MetodoPago() {
    }

    // Constructor con parámetros
    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    // --- Getters y Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}