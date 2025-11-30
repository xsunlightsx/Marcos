package com.example.demo.service;

import com.example.demo.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Usuario save(Usuario usuario);
    void deleteById(Integer id);
    Usuario findByNombreUsuario(String nombreUsuario);
}
