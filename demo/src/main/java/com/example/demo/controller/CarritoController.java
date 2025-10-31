package com.example.demo.controller;

import com.example.demo.model.ItemCarrito; 
import com.example.demo.JPA.LibroRepository;
import com.example.demo.model.Libro;

import jakarta.servlet.http.HttpSession; // Lo mantenemos por si acaso, aunque no es necesario
// para el atributo 'carrito' si usamos @SessionAttributes

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Indica que el atributo "carrito" y "total" se deben almacenar en la sesión HTTP
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
    public List<ItemCarrito> inicializarCarrito() { 
        return new ArrayList<>();
    }

    // Inicializa el total si aún no existe
    @ModelAttribute("total")
    public BigDecimal inicializarTotal() {
        return BigDecimal.ZERO;
    }

    // Muestra la vista del carrito (La ruta /carrito por defecto)
    @GetMapping
    // Nota: El Model model es redundante si solo pasas atributos de sesión, pero es buena práctica mantenerlo.
    public String verCarrito(@ModelAttribute("carrito") List<ItemCarrito> carrito) { 
        // El atributo 'carrito' ya está en el Model gracias a @SessionAttributes
        return "compras"; // Retornamos a 'compras.html' o 'carrito.html', elijo 'compras' por la estructura
    }

    // Añade un libro al carrito desde el catálogo
    @PostMapping("/agregar")
    public String agregarAlCarrito(@RequestParam Long idLibro, 
                                   @RequestParam(defaultValue = "1") int cantidad,
                                   // ⭐ CORRECCIÓN CLAVE: Inyectamos la lista de carrito de la sesión ⭐
                                   @ModelAttribute("carrito") List<ItemCarrito> carrito) {
        
        // No necesitamos HttpSession session aquí, la eliminamos de los argumentos si no la usamos

        Optional<Libro> libroOpt = libroRepository.findById(idLibro);

        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            boolean existe = false;
            
            // 1. Verificar si el libro ya está en el carrito
            for (ItemCarrito item : carrito) {
                if (item.getIdLibro().equals(idLibro)) {
                    // Si existe, incrementa la cantidad y RECALCULA el subtotal
                    item.setCantidad(item.getCantidad() + cantidad);
                    existe = true;
                    break;
                }
            }

            // 2. Si no existe, crear un nuevo ItemCarrito 
            if (!existe) {
                // Uso del constructor completo
                ItemCarrito nuevoItem = new ItemCarrito(
                    libro.getId(), 
                    libro.getNombre(), 
                    libro.getPrecio(), 
                    cantidad
                ); 
                carrito.add(nuevoItem);
            }
        } 
        
        // Redirige al método @GetMapping ("/") que muestra el carrito
        return "redirect:/carrito"; 
    }
    
    // Elimina un item del carrito
    @GetMapping("/eliminar/{id}")
    public String eliminarItem(@PathVariable Long id, @ModelAttribute("carrito") List<ItemCarrito> carrito) { 
        carrito.removeIf(item -> item.getIdLibro().equals(id));
        return "redirect:/carrito"; 
    }
    
    /**
     * Este método se llama antes de cada petición para garantizar que el
     * atributo 'total' en la sesión esté actualizado.
     */
    @ModelAttribute("total")
    public BigDecimal actualizarTotal(@ModelAttribute("carrito") List<ItemCarrito> carrito) { 
        BigDecimal total = carrito.stream()
            .map(ItemCarrito::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return total;
    }
}