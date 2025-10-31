package com.example.demo.model;

import java.math.BigDecimal; 

public class DetalleLibro {

    private Long idLibro;
    private String nombre;
    private BigDecimal precio;
    private Integer cantidad;
    private BigDecimal subtotal;

    public DetalleLibro() {
    }

    public DetalleLibro(Libro libro, int cantidad) {
        this.idLibro = libro.getId();
        this.nombre = libro.getNombre();
        this.precio = libro.getPrecio(); 
        this.cantidad = cantidad;
        this.subtotal = this.precio.multiply(BigDecimal.valueOf(cantidad));
    }

    public void calcularSubtotal() {
        this.subtotal = this.precio.multiply(BigDecimal.valueOf(this.cantidad));
    }

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
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}