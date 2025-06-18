package main.controller;

import main.repository.UserRepository;

public class LoginController {
    private UserRepository userRepository;  // Declaración del campo

    // Constructor
    public LoginController() {
        this.userRepository = new UserRepository();  // Inicializa el repositorio
    }

    
    public boolean validarUsuario(String email, String password) {
        return userRepository.validarUsuario(email, password);
        }
}