package com.example.demo.JPA;

import com.example.demo.model.Libro; // Importa la clase Entidad Libro
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository 
public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByNombre(String nombre);

}