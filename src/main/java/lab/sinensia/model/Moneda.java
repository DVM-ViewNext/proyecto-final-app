package lab.sinensia.model;

public class Moneda {

	private int id;
	private int idtipomoneda;
	private String nombre;
	private double precio;
	
	public Moneda() {

	}
	
	public Moneda(int idtipomoneda, String nombre, double precio) {
		super();
		this.idtipomoneda = idtipomoneda;
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdtipomoneda() {
		return idtipomoneda;
	}

	public void setIdtipomoneda(int idtipomoneda) {
		this.idtipomoneda = idtipomoneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
}
