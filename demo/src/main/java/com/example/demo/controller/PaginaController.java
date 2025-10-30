package com.example.demo.controller;

import com.example.demo.JPA.LibroRepository; // <-- Importar el Repository
import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List; // <-- Asegúrate de tener este import también

@Controller
public class PaginaController {

    private final LibroRepository libroRepository; // <-- 1. Declarar el repositorio
    
    // <-- 2. Inyectar el repositorio usando el constructor
    public PaginaController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio"; 
    }

    @GetMapping("/catalogo")
    public String verCatalogo(Model model) { 
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros); 
        return "catalogo"; 
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros"; 
    }

    @GetMapping("/compras")
    public String compras(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("carrito", new ArrayList<>());

        model.addAttribute("total", 0.0);

        return "compras";
    }
}