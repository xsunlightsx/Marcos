package com.example.demo.controller;

import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    private List<Libro> carrito = new ArrayList<>();

    @GetMapping
    public String mostrarCarrito(Model model) {
        double total = carrito.stream().mapToDouble(Libro::getSubtotal).sum();
        model.addAttribute("carrito", carrito);
        model.addAttribute("total", total);
        model.addAttribute("libro", new Libro());
        return "compras"; 
    }

    @PostMapping("/agregar")  
    public String agregarLibro(@ModelAttribute Libro libro) {
        carrito.add(libro);
        return "redirect:/carrito"; 
    }

    @GetMapping("/eliminar/{index}")
    public String eliminarLibro(@PathVariable int index) {
        if (index >= 0 && index < carrito.size()) {
            carrito.remove(index);
        }
        return "redirect:/carrito";
    }
}

