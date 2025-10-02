package com.example.demo.dao;

/*import com.example.demo.model.Libro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LibroDAO {
private final JdbcTemplate jdbcTemplate;

    public LibroDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Insertar libro
    public void insertar(String titulo, double precio) {
        String sql = "INSERT INTO libros (titulo, precio) VALUES (?, ?)";
        jdbcTemplate.update(sql, titulo, precio);
    }

    // Listar libros
    public List<Libro> listar() {
        String sql = "SELECT * FROM libros";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Libro(rs.getString("titulo"), rs.getDouble("precio"), rs.getInt("cantidad")));
    }

    // Eliminar libro
    public void eliminar(Long id) {
        String sql = "DELETE FROM libros WHERE id = ?";
        jdbcTemplate.update(sql, id);
    } 
}
*/
