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
    private Long contadorId = 1L;

    @GetMapping("/nuevo")
    public String nuevoLibroForm() {
        return "nuevo-libro"; // vista formulario
    }

    @PostMapping("/guardar")
    public String guardarLibro(@RequestParam String titulo, @RequestParam double precio) {
        libros.add(new Libro(contadorId++, titulo, precio));
        return "redirect:/libros/lista";
    }

    @GetMapping("/lista")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libros);
        return "lista-libros"; // vista listado
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libros.removeIf(l -> l.getId().equals(id));
        return "redirect:/libros/lista";
    }
}
