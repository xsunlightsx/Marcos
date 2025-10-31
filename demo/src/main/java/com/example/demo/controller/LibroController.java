package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.JPA.LibroRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
            @RequestParam String precio, 
            @RequestParam String autor,
            @RequestParam String descripcion,
            @RequestParam int cantidad, 
            @RequestParam String imagenUrl) {
    
        BigDecimal precioDecimal = new BigDecimal(precio);
        
        Libro nuevoLibro = new Libro(nombre, precioDecimal, autor, descripcion, imagenUrl);

        nuevoLibro.setCantidad(cantidad); 
        
        libroRepository.save(nuevoLibro); 
        
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