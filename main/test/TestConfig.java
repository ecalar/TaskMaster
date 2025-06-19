package main.test;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.DriverManager;

public class TestConfig {
    public static void main(String[] args) throws Exception {
        // Prueba BCrypt
        System.out.println("[1] Prueba BCrypt:");
        String hash = BCrypt.hashpw("test123", BCrypt.gensalt());
        System.out.println("Hash: " + hash);
        
        // Prueba SQLite
        System.out.println("\n[2] Prueba SQLite:");
        DriverManager.getConnection("jdbc:sqlite:test.db");
        System.out.println("¡Conexión exitosa!");
    }
}