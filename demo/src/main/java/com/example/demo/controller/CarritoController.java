package com.example.demo.controller;

// ¡Asegúrate de que la ruta de ItemCarrito sea correcta!
import com.example.demo.model.ItemCarrito; 
import com.example.demo.JPA.LibroRepository;
import com.example.demo.model.Libro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Indica que el atributo "carrito" se debe almacenar en la sesión HTTP
@Controller
@SessionAttributes({"carrito", "total"})
@RequestMapping("/carrito")
public class CarritoController {

    private final LibroRepository libroRepository;

    public CarritoController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Inicializa el carrito si aún no existe en la sesión
    @ModelAttribute("carrito")
    public List<ItemCarrito> inicializarCarrito() { // CAMBIO AQUÍ
        return new ArrayList<>();
    }

    // Inicializa el total si aún no existe
    @ModelAttribute("total")
    public BigDecimal inicializarTotal() {
        return BigDecimal.ZERO;
    }

    // Muestra la vista del carrito
    @GetMapping
    public String verCarrito(@ModelAttribute("carrito") List<ItemCarrito> carrito, Model model) { // CAMBIO AQUÍ
        return "carrito"; 
    }

    // Añade un libro al carrito desde el catálogo
    @PostMapping("/agregar/{id}")
    public String agregarAlCarrito(@PathVariable Long id, 
                                   @RequestParam(defaultValue = "1") int cantidad,
                                   @ModelAttribute("carrito") List<ItemCarrito> carrito) { // CAMBIO AQUÍ
        
        Optional<Libro> libroOpt = libroRepository.findById(id);

        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            
            // 1. Verificar si el libro ya está en el carrito
            Optional<ItemCarrito> itemExistente = carrito.stream() // CAMBIO AQUÍ
                .filter(item -> item.getIdLibro().equals(id)) // Asume que ItemCarrito tiene getIdLibro()
                .findFirst();

            if (itemExistente.isPresent()) {
                // Si existe, solo actualiza la cantidad
                ItemCarrito item = itemExistente.get(); // CAMBIO AQUÍ
                item.setCantidad(item.getCantidad() + cantidad);
                // Asume que ItemCarrito tiene calcularSubtotal() y setCantidad()
                // item.calcularSubtotal(); // Ya se llama dentro de setCantidad en ItemCarrito
            } else {
                // Si no existe, crea un nuevo item
                // Debes crear un constructor en ItemCarrito que tome el objeto Libro y la cantidad
                ItemCarrito nuevoItem = new ItemCarrito(
                    libro.getId(), 
                    libro.getNombre(), 
                    libro.getPrecio(), 
                    cantidad // Asume que ItemCarrito se inicializa correctamente con estos datos
                );
                carrito.add(nuevoItem);
            }
        }
        
        return "redirect:/catalogo"; 
    }
    
    // Elimina un item del carrito
    @GetMapping("/eliminar/{id}")
    public String eliminarItem(@PathVariable Long id, @ModelAttribute("carrito") List<ItemCarrito> carrito) { // CAMBIO AQUÍ
        carrito.removeIf(item -> item.getIdLibro().equals(id));
        return "redirect:/carrito"; 
    }
    
    // Método que calcula el total cada vez que el carrito se actualiza
    @ModelAttribute("total")
    public BigDecimal actualizarTotal(@ModelAttribute("carrito") List<ItemCarrito> carrito) { // CAMBIO AQUÍ
        BigDecimal total = carrito.stream()
            .map(ItemCarrito::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}