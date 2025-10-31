package com.example.demo.JPA;

import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    // Métodos de consulta personalizados si son necesarios
}