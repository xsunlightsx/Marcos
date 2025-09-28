package com.example.demo.model;

public class Libro {
    private Long id;
    private String titulo;
    private double precio;

    public Libro(){

    }

    public Libro(Long id, String titulo, double precio){
        this.id=id;
        this.titulo=titulo;
        this.precio=precio;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    public double getPrecio(){
        return precio;
    }

    public void setPrecio(double precio){
        this.precio=precio;
    }

}
