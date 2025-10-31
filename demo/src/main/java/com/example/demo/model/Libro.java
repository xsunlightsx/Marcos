package com.example.demo.model;

import jakarta.persistence.*; 
import java.math.BigDecimal;

@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nombre;

    @Column(name = "precio", precision = 10, scale = 2) 
    private BigDecimal precio; 
    private int cantidad;
    private int stock;
    private String autor; 
    
    @Column(length = 1000) 
    private String descripcion;
    
    @Column(name = "imagen_url")
    private String imagenUrl;

    public Libro() {}


    public Libro(String nombre, BigDecimal precio, String autor, String descripcion, String imagenUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.autor = autor;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.cantidad = 0; 
    }


    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; } 

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}
