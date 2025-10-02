package com.example.demo.controller;

import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class PaginaController {

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio"; 
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo"; 
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros"; 
    }

    @GetMapping("/compras")
    public String compras(Model model) {
        model.addAttribute("libro", new Libro());

        // carrito vacío por defecto
        model.addAttribute("carrito", new ArrayList<>());

        // total inicial
        model.addAttribute("total", 0.0);

        return "compras";
    }
}