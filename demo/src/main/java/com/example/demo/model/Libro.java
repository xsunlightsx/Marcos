package com.example.demo.model;

public class Libro {
    private String nombre;
    private double precio;
    private int cantidad;

    public Libro() {}

    public Libro(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 1; // por defecto 1 unidad
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return precio * cantidad; }
}