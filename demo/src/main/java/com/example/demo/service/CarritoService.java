package com.example.demo.service;

import com.example.demo.model.Carrito;
import com.example.demo.model.ItemCarrito;
import com.example.demo.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private Carrito carrito = new Carrito();

    public void agregarProducto(Libro libro, int cantidad) {
        ItemCarrito item = new ItemCarrito(libro, cantidad);
        carrito.agregarItem(item);
    }

    public List<ItemCarrito> listarProductos() {
        return carrito.getItems();
    }

    public Optional<ItemCarrito> consultarProducto(Long idLibro) {
        return carrito.getItems().stream()
                .filter(item -> item.getLibro().getId().equals(idLibro))
                .findFirst();
    }

    public boolean eliminarProducto(Long idLibro) {
        Optional<ItemCarrito> item = consultarProducto(idLibro);
        if (item.isPresent()) {
            carrito.eliminarItem(item.get());
            return true;
        }
        return false;
    }

    public List<ItemCarrito> buscarPorTitulo(String titulo) {
        return carrito.getItems().stream()
                .filter(item -> item.getLibro().getTitulo().toLowerCase()
                        .contains(titulo.toLowerCase()))
                .toList();
    }

    public double obtenerTotal() {
        return carrito.getTotal();
    }
}