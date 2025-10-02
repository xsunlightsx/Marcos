package com.example.demo.service;

import com.example.demo.model.Testimonio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestimonioService {
    private List<Testimonio> testimonios = new ArrayList<>();
    private int contadorId = 1;


    public void agregarTestimonio(String cliente, String comentario, int calificacion) {
        testimonios.add(new Testimonio(contadorId++, cliente, comentario, calificacion));
    }

    public List<Testimonio> listarTestimonios() {
        return testimonios;
    }

    public List<Testimonio> buscarPorCliente(String nombre) {
        return testimonios.stream()
                .filter(t -> t.getCliente().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void eliminarTestimonio(int id) {
        testimonios.removeIf(t -> t.getId() == id);
    }

  
    public double promedioCalificacion() {
        return testimonios.isEmpty() ? 0 :
                testimonios.stream().mapToInt(Testimonio::getCalificacion).average().orElse(0);
    }
}
