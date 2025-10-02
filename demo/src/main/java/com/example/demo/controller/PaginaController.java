package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginaController {

    @GetMapping("/inicio")
    public String inicio() {
        return "inicio"; // → templates/inicio.html
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo"; // → templates/catalogo.html
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros"; // → templates/nosotros.html
    }

    @GetMapping("/comprar")
    public String comprar() {
        return "comprar"; // → templates/comprar.html
    }
}
