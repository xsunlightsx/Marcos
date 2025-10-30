package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.JPA.LibroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroRepository libroRepository;

    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @GetMapping("/nuevo")
    public String nuevoLibroForm() {
        return "nuevo-libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(
            @RequestParam String nombre, 
            @RequestParam double precio,
            // ⭐ CAMPOS NUEVOS AÑADIDOS ⭐
            @RequestParam String autor,
            @RequestParam String descripcion,
            @RequestParam String imagenUrl) {
        
        // 1. Crear el objeto Libro usando el nuevo constructor
        Libro nuevoLibro = new Libro(nombre, precio, autor, descripcion, imagenUrl);
        
        // 2. Guardar en la base de datos (Persistencia)
        libroRepository.save(nuevoLibro); 
        
        // 3. Redirigir a la lista de libros
        return "redirect:/libros/lista";
    }

    @GetMapping("/lista")
    public String listarLibros(Model model) {
        List<Libro> libros = libroRepository.findAll();
        model.addAttribute("libros", libros);
        return "lista-libros";
    }

    @GetMapping("/eliminar/{nombre}")
    public String eliminarLibro(@PathVariable String nombre) {
        Optional<Libro> libroOpt = libroRepository.findByNombre(nombre);
        
        if (libroOpt.isPresent()) {
            libroRepository.delete(libroOpt.get());
        }
        
        return "redirect:/libros/lista";
    }
}