package main.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import main.controller.LoginController;
import java.util.Arrays;

public class LoginView extends JFrame {
	
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private static final long serialVersionUID = 1L;
	private LoginController loginController; //Referencia al controlador.
	
	public LoginView() {
		loginController = new LoginController();
		
		setTitle("TaskMaster - Login");
		setSize(300,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		
		txtEmail = new JTextField(20);
		txtPassword = new JPasswordField(20);
		JButton btnLogin = new JButton("Iniciar Sesión");
		
		
		panel.add(new JLabel("Email"));
		panel.add(txtEmail);
		panel.add(new JLabel("Contraseña"));
		panel.add(txtPassword);
		panel.add(btnLogin);
		
		
		btnLogin.addActionListener(this::onLogicClicked);
		add(panel);
	}
	
	
	private void onLogicClicked(ActionEvent e) {
		
		String email = txtEmail.getText();
		char[] passwordChars = txtPassword.getPassword();
		String password = new String(passwordChars);
		
		
		if (loginController.validarUsuario(email, password)) {
			JOptionPane.showMessageDialog(this, "¡Login exitoso!");
		}else{
			
			JOptionPane.showMessageDialog(this, "Credenciales Incorrectas", "Error", JOptionPane.ERROR_MESSAGE);	
		}
		
		Arrays.fill(passwordChars, '0');
		txtPassword.setText("");
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			LoginView view = new LoginView();
			view.setVisible(true);
		});
	}

}
