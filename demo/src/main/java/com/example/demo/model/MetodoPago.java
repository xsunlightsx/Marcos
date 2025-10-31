package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo")
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre; 

    @Column(name = "activo", nullable = false)
    private Boolean activo = true; 

    public MetodoPago() {
    }

    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

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
