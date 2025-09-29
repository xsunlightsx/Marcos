package com.example.demo.controller;

import com.example.demo.service.PersonaService;
import com.example.demo.model.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/ingreso")
    public String ingresoForm() {
        return "ingreso";
    }

    @PostMapping("/ingreso")
    public String guardar(@RequestParam String nombre, @RequestParam String correo) {
        personaService.agregar(nombre, correo);
        return "redirect:/listado";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("personas", personaService.listar());
        return "listado";
    }

    @GetMapping("/consulta")
    public String consultaForm() {
        return "consulta";
    }

    @PostMapping("/consulta")
    public String consulta(@RequestParam int id, Model model) {
        Persona p = personaService.buscar(id);
        model.addAttribute("persona", p);
        return "consulta";
    }

    @GetMapping("/eliminar")
    public String eliminarForm() {
        return "eliminar";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam int id) {
        personaService.eliminar(id);
        return "redirect:/listado";
    }
}
