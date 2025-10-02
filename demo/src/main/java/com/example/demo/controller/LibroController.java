package com.example.demo.controller;

import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final List<Libro> libros = new ArrayList<>();

    @GetMapping("/nuevo")
    public String nuevoLibroForm() {
        return "nuevo-libro"; 
    }

    @PostMapping("/guardar")
    public String guardarLibro(@RequestParam String nombre, @RequestParam double precio) {
        libros.add(new Libro(nombre, precio));
        return "redirect:/libros/lista";
    }
    @GetMapping("/lista")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libros);
        return "lista-libros"; 
    }

    @GetMapping("/eliminar/{nombre}")
    public String eliminarLibro(@PathVariable String nombre) {
        libros.removeIf(l -> l.getNombre().equals(nombre));
        return "redirect:/libros/lista";
    }
}
