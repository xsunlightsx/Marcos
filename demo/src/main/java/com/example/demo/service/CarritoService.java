package com.example.demo.service;

import com.example.demo.model.Carrito;
import com.example.demo.model.ItemCarrito;
import com.example.demo.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public double obtenerTotal() {
        return carrito.getTotal();
    }
}