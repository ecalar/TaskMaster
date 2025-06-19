package main.test;

import main.repository.UserRepository;

public class TestAuth {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        
        // 1. Prueba de registro
        String email = "test@taskmaster.com";
        String password = "admin123";
        
        System.out.println("=== Registro de usuario ===");
        boolean registroExitoso = userRepo.registrarUsuario(email, password);
        System.out.println("Registro exitoso: " + registroExitoso);
        
        // 2. Prueba de login con credenciales correctas
        System.out.println("\n=== Login (credenciales válidas) ===");
        boolean loginValido = userRepo.validarUsuario(email, password);
        System.out.println("Login exitoso: " + loginValido);
        
        // 3. Prueba de login con credenciales incorrectas
        System.out.println("\n=== Login (credenciales inválidas) ===");
        boolean loginInvalido = userRepo.validarUsuario(email, "claveEquivocada");
        System.out.println("Login exitoso: " + loginInvalido);  // Debe ser false
    }
}