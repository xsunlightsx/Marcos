package com.example.demo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime; // Importado para fecha y hora
import java.util.List;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    // Relación ManyToOne: Una venta pertenece a un cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false) // Asumiendo que id_cliente no puede ser nulo
    private Cliente cliente;

    // Relación ManyToOne: Una venta tiene un método de pago
    // Necesitas crear la entidad MetodoPago.java si aún no existe.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pago_id", nullable = false) 
    private MetodoPago metodoPago; 

    // Usamos LocalDateTime para tener fecha y hora de la transacción
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    // Se mantiene BigDecimal para precisión monetaria
    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_entrega", nullable = false)
    private TipoEntrega tipoEntrega;
    
    // Relación OneToMany: Una venta tiene muchos detalles
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    public enum TipoEntrega {
        digital, delivery
    }

    // Constructor por defecto (requerido por JPA)
    public Venta() {
        // Inicializa la fecha y hora de la venta al momento de la creación
        this.fecha = LocalDateTime.now(); 
    }

    // Constructor con parámetros (útil para pruebas o inicialización manual)
    public Venta(Cliente cliente, MetodoPago metodoPago, BigDecimal total, TipoEntrega tipoEntrega) {
        this.cliente = cliente;
        this.metodoPago = metodoPago;
        this.total = total;
        this.tipoEntrega = tipoEntrega;
        this.fecha = LocalDateTime.now(); 
    }

    // Getters y Setters
    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(TipoEntrega tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}