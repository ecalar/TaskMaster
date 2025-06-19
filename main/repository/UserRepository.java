package main.repository;

import java.sql.Connection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
    private static final String DB_URL = "jdbc:sqlite:taskmaster.db";

    public UserRepository() {
        crearTablaUsuarios();  // Asegura que la tabla exista
    }
    
    public boolean registrarUsuario(String email, String password) {
        String sql = "INSERT INTO usuarios(email, password) VALUES(?, ?)";
    
    try (Connection conn = DriverManager.getConnection(DB_URL);
    		PreparedStatement pstmt = conn.prepareStatement(sql)){
    	
    	pstmt.setString(1, email);
    	pstmt.setString(2, hashPassword(password));
    	
    	pstmt.executeUpdate();
    	return true;
    	
    } catch (SQLException e) {
    	
    	System.err.println("Error al registrar usuario: " + e.getMessage());
    	return false;
    }
}

    public String hashPassword(String password) {
    	return BCrypt.hashpw(password, BCrypt.gensalt());
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
                String hashGuardado = rs.getString("password");
                return BCrypt.checkpw(password, hashGuardado); // En producción, usa BCrypt
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}