package com.example.demo.controller;

import com.example.demo.repository.LibroRepository;
import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PaginaController {

    private final LibroRepository libroRepository; 
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
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }
}