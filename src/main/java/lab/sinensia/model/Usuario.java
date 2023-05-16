package lab.sinensia.model;

public class Usuario {

	private int id;
	private String nombre;
	private String usuario;
	private String password;
	private String email;
	private String dni;

	public Usuario() {

	}

	public Usuario(String nombre, String usuario, String password, String email, String dni) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", password=" + password
				+ ", email=" + email + ", dni=" + dni + "]";
	}


	
	
}
