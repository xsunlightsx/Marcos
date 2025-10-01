package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class PaginaController {
    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("contenido", "inicio :: contenido");
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("contenido", "catalogo :: contenido");
        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("contenido", "nosotros :: contenido");
        return "index";
    }

    @GetMapping("/carrito")
    public String carrito(Model model) {
        model.addAttribute("contenido", "carrito :: contenido");
        return "index";
    }
}
