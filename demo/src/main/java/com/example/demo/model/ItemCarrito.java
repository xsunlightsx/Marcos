package com.example.demo.model; 

import java.io.Serializable;
import java.math.BigDecimal; 
import java.util.Objects; 

public class ItemCarrito implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idLibro; 
    private String nombre; // Título del libro
    private Integer cantidad;
    private BigDecimal precio; // Precio unitario (usando BigDecimal para precisión)
    private BigDecimal subtotal;

    // Constructor vacío (requerido por algunas librerías)
    public ItemCarrito() {
    }

    // Constructor principal
    public ItemCarrito(Long idLibro, String nombre, BigDecimal precio, Integer cantidad) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
    }

    // Método para calcular el subtotal (útil para actualizar la cantidad)
    public void calcularSubtotal() {
        this.subtotal = this.precio.multiply(BigDecimal.valueOf(this.cantidad));
    }

    // --- Getters y Setters ---

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        // Recalcular el subtotal cada vez que se cambia la cantidad
        calcularSubtotal();
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
        calcularSubtotal();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    // No se necesita setSubtotal ya que se calcula automáticamente

    // --- Métodos de utilidad (opcional, pero buena práctica para colecciones) ---
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarrito that = (ItemCarrito) o;
        return Objects.equals(idLibro, that.idLibro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro);
    }
}