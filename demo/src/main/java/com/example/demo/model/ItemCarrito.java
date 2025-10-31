package com.example.demo.model; 

import java.io.Serializable;
import java.math.BigDecimal; 
import java.util.Objects; 

public class ItemCarrito {

    private Long idLibro;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal subtotal; // Almacena el precio * cantidad

    // Constructor vacío (necesario para algunas operaciones y librerías)
    public ItemCarrito() {
    }

    // ⭐ CONSTRUCTOR CORRECTO QUE INCLUYE EL CÁLCULO DEL SUBTOTAL ⭐
   public ItemCarrito(Long idLibro, String nombre, BigDecimal precio, Integer cantidad) { 
    this.idLibro = idLibro;
    this.nombre = nombre;
    this.precio = precio;
    this.cantidad = cantidad;
    this.subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        // ⚠️ IMPORTANTE: Si la cantidad cambia, el subtotal debe recalcularse
        if (this.precio != null) {
            this.subtotal = this.precio.multiply(BigDecimal.valueOf(cantidad));
        }
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