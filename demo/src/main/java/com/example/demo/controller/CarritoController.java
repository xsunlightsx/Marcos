package com.example.demo.controller;

import com.example.demo.model.Libro;
import com.example.demo.service.CarritoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/compras")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public String mostrarCompras(Model model) {
        model.addAttribute("items", carritoService.listarProductos());
        model.addAttribute("total", carritoService.obtenerTotal());
        return "comprar"; 
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam String titulo,
                                  @RequestParam double precio,
                                  @RequestParam int cantidad) {

        Libro libro = new Libro();
        libro.setId(idLibro);
        libro.setTitulo("Libro " + idLibro);
        libro.setPrecio(10.0);

        carritoService.agregarProducto(libro, cantidad);

        return "redirect:/compras";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        carritoService.eliminarProducto(id);
        return "redirect:/compras";
    }
}

