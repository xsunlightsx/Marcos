package com.example.demo.service;

import com.example.demo.model.Persona;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PersonaService {
    private List<Persona> personas = new ArrayList<>();
    private int contadorId = 1;

    public void agregar(String nombre, String correo) {
        personas.add(new Persona(contadorId++, nombre, correo));
    }

    public List<Persona> listar() {
        return personas;
    }

    public Persona buscar(int id) {
        return personas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void eliminar(int id) {
        personas.removeIf(p -> p.getId() == id);
    }
}
