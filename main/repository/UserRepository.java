package main.repository;

import main.model.Usuario;
import java.sql.*;
import java.util.Arrays;

public class UserRepository {
    private static final String DB_URL = "jdbc:sqlite:taskmaster.db";

    public UserRepository() {
        crearTablaUsuarios();  // Asegura que la tabla exista
    }

    private void crearTablaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                     "id TEXT PRIMARY KEY, " +
                     "email TEXT UNIQUE NOT NULL, " +
                     "password TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validarUsuario(String email, String password) {
        String sql = "SELECT password FROM usuarios WHERE email = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // En producción, usa BCrypt
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}