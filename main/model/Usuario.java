package main.model;

public class Usuario {
	private String id;
	private String nombre;
	private String email;
	private String password;
	
	//Constructor
	
	public Usuario(String email, String password) {
		this.id = java.util.UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
	}
	
	//Getters y Setters
	
	public String getId() { return id; }
	public String getnombre() { return nombre; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

}



