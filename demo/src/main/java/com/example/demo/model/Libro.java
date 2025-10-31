package com.example.demo.model;

import jakarta.persistence.*; 
import java.math.BigDecimal;

@Entity
@Table(name = "libro") // Correcto: tabla 'libro' (singular)
public class Libro {
    
    // 1. CORRECCIÓN: Usamos 'id' (sin @Column) porque la columna en la DB se llama 'id'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String nombre;
    
    // Mapeo del precio
    @Column(name = "precio", precision = 10, scale = 2) 
    private BigDecimal precio; 
    
    // 2. CORRECCIÓN CRÍTICA: Revertimos el nombre del campo de Java a 'cantidad' 
    //    para mapear la columna 'cantidad' de la DB que contiene los valores reales.
    private int cantidad; 
    
    private String autor; 
    
    @Column(length = 1000) 
    private String descripcion;
    
    @Column(name = "imagen_url")
    private String imagenUrl;
    
    // Constructor vacío
    public Libro() {}

    // Constructor que usa el LibroController
    public Libro(String nombre, BigDecimal precio, String autor, String descripcion, String imagenUrl) {
        this.nombre = nombre;
        this.precio = precio;
        this.autor = autor;
        this.descripcion = descripcion;
        this.imagenUrl = imagenUrl;
        this.cantidad = 0; // Inicializar a 0 o se asigna en el constructor con el valor del formulario
    }

    // --- Getters and Setters ---
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; } 

    // 3. CORRECCIÓN: Getters/Setters para el campo 'cantidad' (stock real)
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
}