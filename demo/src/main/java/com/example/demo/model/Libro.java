package com.example.demo.model;

import jakarta.persistence.*; // Assuming this import issue is fixed by pom.xml update

@Entity
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private double precio;
    private int cantidad;
    private String autor; 

    @Column(length = 1000) // Good practice for longer text fields like descriptions
    private String descripcion;
    
    private String imagenUrl; // Path to the image, e.g., "img/lib1.jpg"

    public Libro() {}

    // Constructor updated to include new fields
    public Libro(String nombre, double precio, String autor, String descripcion, String imagenUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.autor = autor;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.cantidad = 1; 
    }
    
    // --- Getters and Setters for all fields ---

    // Existing methods
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    // New methods
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }

    // Helper method
    public double getSubtotal() { return precio * cantidad; }
}