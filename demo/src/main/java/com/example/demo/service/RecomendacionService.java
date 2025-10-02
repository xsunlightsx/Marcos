package com.example.demo.service;

import com.example.demo.model.Recomendacion;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecomendacionService {
    private List<Recomendacion> recomendaciones = new ArrayList<>();

    public void agregar(Recomendacion r) {
        recomendaciones.add(r);
    }

    public List<Recomendacion> listar() {
        return recomendaciones;
    }

    public void eliminar(int index) {
        if (index >= 0 && index < recomendaciones.size()) {
            recomendaciones.remove(index);
        }
    }
}
