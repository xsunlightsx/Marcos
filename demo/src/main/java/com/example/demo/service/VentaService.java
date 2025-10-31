package com.example.demo.service;

import com.example.demo.model.ItemCarrito; // Asegúrate de que esta ruta sea correcta
import com.example.demo.model.Cliente;
import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Libro;
import com.example.demo.model.MetodoPago;
import com.example.demo.model.Venta;
import com.example.demo.model.Venta.TipoEntrega; // Importamos el Enum TipoEntrega
import com.example.demo.JPA.DetalleVentaRepository;
import com.example.demo.JPA.LibroRepository; 
import com.example.demo.JPA.VentaRepository; // Corregido: Asumiendo que VentaRepository está en la carpeta JPA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal; // Necesario para el total
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    @Autowired
    private LibroRepository libroRepository; 

    /**
     * Procesa la compra: guarda la venta y sus detalles en la BD.
     */
    @Transactional
    public Venta procesarCompra(List<ItemCarrito> carrito, Cliente cliente, MetodoPago metodoPago, 
                                TipoEntrega tipoEntrega, BigDecimal totalCalculado) { // <-- Tipos de datos corregidos
        
        // 1. Crear y guardar la Venta principal (Encabezado)
        Venta nuevaVenta = new Venta();
        nuevaVenta.setCliente(cliente);
        nuevaVenta.setMetodoPago(metodoPago);
        nuevaVenta.setTipoEntrega(tipoEntrega);
        nuevaVenta.setTotal(totalCalculado);

        Venta ventaGuardada = ventaRepository.save(nuevaVenta);

        // 2. Iterar sobre el carrito y guardar los Detalles de Venta
        for (ItemCarrito item : carrito) {
            
            // Buscar la entidad Libro por ID
            Libro libro = libroRepository.findById(item.getIdLibro())
                .orElseThrow(() -> new RuntimeException("Error fatal: Libro no encontrado con ID: " + item.getIdLibro() + ". Compra abortada."));
            
            DetalleVenta detalle = new DetalleVenta();
            
            detalle.setVenta(ventaGuardada); 
            detalle.setLibro(libro);
            detalle.setCantidad(item.getCantidad());
            
            // Usamos BigDecimal para el precio unitario, asumiendo que DetalleVenta.precioUnitario es BigDecimal
            detalle.setPrecioUnitario(item.getPrecio()); 

            detalleVentaRepository.save(detalle);
        }

        return ventaGuardada;
    }
}