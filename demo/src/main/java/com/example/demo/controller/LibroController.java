package com.example.demo.controller;

import com.example.demo.dao.LibroDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroDAO libroDAO;

    public LibroController(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    @GetMapping("/nuevo")
    public String nuevoLibroForm() {
        return "nuevo-libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@RequestParam String titulo, @RequestParam double precio) {
        libroDAO.insertar(titulo, precio);
        return "redirect:/libros/lista";
    }

    @GetMapping("/lista")
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroDAO.listar());
        return "lista-libros"; 
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroDAO.eliminar(id);
        return "redirect:/libros/lista";
    }
}
