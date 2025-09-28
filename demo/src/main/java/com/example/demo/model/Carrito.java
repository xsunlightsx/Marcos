package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> item = new ArrayList<>();

    public Carrito(){

    }

    public List<ItemCarrito> getItems(){
        return item;
    }

    public void setItems(List<ItemCarrito> item){
        this.item=item;
    }

    public double getTotal(){
        return item.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }

    public void agregarItem(ItemCarrito item) {
        this.item.add(item);
    }

    public void eliminarItem(ItemCarrito item) {
        this.item.remove(item);
    }

    public void limpiar() {
        this.item.clear();
    }
}
