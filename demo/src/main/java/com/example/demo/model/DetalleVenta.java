package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne(fetch = FetchType.LAZY) // Muchos detalles pertenecen a una venta
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY) // Muchos detalles apuntan a un libro
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    // Campo corregido: Guardamos el precio unitario del libro al momento de la venta
    @Column(name = "precio_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioUnitario; 
    
    // NOTA: Se ha eliminado el campo 'subtotal' de la entidad para simplificar la persistencia.

    // Getters y Setters
    public Integer getIdDetalle() {
        return idDetalle;
    }
    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }
    public Venta getVenta() {
        return venta;
    }
    // Añadido FetchType.LAZY y nullable=false a las relaciones
    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    // Getter y Setter para el nuevo campo 'precioUnitario'
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}