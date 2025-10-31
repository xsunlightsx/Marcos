package com.example.demo.service;

import com.example.demo.model.ItemCarrito; 
import com.example.demo.model.Cliente;
import com.example.demo.model.DetalleVenta;
import com.example.demo.model.Libro;
import com.example.demo.model.MetodoPago;
import com.example.demo.model.Venta;
import com.example.demo.model.Venta.TipoEntrega; 
import com.example.demo.JPA.DetalleVentaRepository;
import com.example.demo.JPA.LibroRepository; 
import com.example.demo.JPA.VentaRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal; 
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    @Autowired
    private LibroRepository libroRepository; 

    @Transactional
    public Venta procesarCompra(List<ItemCarrito> carrito, Cliente cliente, MetodoPago metodoPago, 
                                TipoEntrega tipoEntrega, BigDecimal totalCalculado) { 
        
        Venta nuevaVenta = new Venta();
        nuevaVenta.setCliente(cliente);
        nuevaVenta.setMetodoPago(metodoPago);
        nuevaVenta.setTipoEntrega(tipoEntrega);
        nuevaVenta.setTotal(totalCalculado);

        Venta ventaGuardada = ventaRepository.save(nuevaVenta);

        for (ItemCarrito item : carrito) {

            Libro libro = libroRepository.findById(item.getIdLibro())
                .orElseThrow(() -> new RuntimeException("Error fatal: Libro no encontrado con ID: " + item.getIdLibro() + ". Compra abortada."));
            
            // Verificar y actualizar el stock
            if (libro.getStock() < item.getCantidad()) {
                throw new RuntimeException("No hay suficiente stock para el libro: " + libro.getNombre());
            }
            libro.actualizarStock(-item.getCantidad()); // Reducir el stock
            libroRepository.save(libro);
            
            DetalleVenta detalle = new DetalleVenta();
            
            detalle.setVenta(ventaGuardada); 
            detalle.setLibro(libro);
            detalle.setCantidad(item.getCantidad());
            
            detalle.setPrecioUnitario(item.getPrecio()); 

            detalleVentaRepository.save(detalle);
        }

        return ventaGuardada;
    }
}
