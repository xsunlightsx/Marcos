package com.example.demo.service.impl;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioServiceImpl(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return repo.save(usuario);
    }

    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public Usuario findByNombreUsuario(String nombreUsuario) {
        return repo.findByNombreUsuario(nombreUsuario).orElse(null);
    }
}
