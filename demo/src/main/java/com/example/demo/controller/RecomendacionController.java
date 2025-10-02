package com.example.demo.controller;

import com.example.demo.model.Recomendacion;
import com.example.demo.service.RecomendacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService service;

    public RecomendacionController(RecomendacionService service) {
        this.service = service;
    }

    @GetMapping
    public String verRecomendaciones(Model model) {
        model.addAttribute("nuevaRecomendacion", new Recomendacion());
        model.addAttribute("recomendaciones", service.listar());
        return "recomendaciones";
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute Recomendacion recomendacion) {
        service.agregar(recomendacion);
        return "redirect:/recomendaciones";
    }

    @GetMapping("/eliminar/{index}")
    public String eliminar(@PathVariable int index) {
        service.eliminar(index);
        return "redirect:/recomendaciones";
    }
}
