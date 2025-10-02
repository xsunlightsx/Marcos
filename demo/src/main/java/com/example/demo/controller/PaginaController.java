package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        model.addAttribute("contenido", "inicio.html :: contenido");
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        model.addAttribute("contenido", "catalogo.html :: contenido");
        return "index";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("contenido", "nosotros.html :: contenido");
        return "index";
    }

    @GetMapping("/comprar")
    public String comprar(Model model) {
        model.addAttribute("contenido", "compras.html :: contenido");
        return "index";
    }
}