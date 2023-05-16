package lab.sinensia.model;

public class Transaccion {

	private int id;
	private int idwallet;
	private int idestatustransaccion;
	private int idtipotransaccion;
	private int referencia;
	private double monto;
	private String concepto;
	private String fecha;
	private String canal;
	private String address;

	public Transaccion() {

	}

	public Transaccion(int idwallet, int idestatustransaccion, int idtipotransaccion, int referencia, double monto,
			String concepto, String fecha, String canal, String address) {
		super();
		this.idwallet = idwallet;
		this.idestatustransaccion = idestatustransaccion;
		this.idtipotransaccion = idtipotransaccion;
		this.referencia = referencia;
		this.monto = monto;
		this.concepto = concepto;
		this.fecha = fecha;
		this.canal = canal;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdwallet() {
		return idwallet;
	}

	public void setIdwallet(int idwallet) {
		this.idwallet = idwallet;
	}

	public int getIdestatustransaccion() {
		return idestatustransaccion;
	}

	public void setIdestatustransaccion(int idestatustransaccion) {
		this.idestatustransaccion = idestatustransaccion;
	}

	public int getIdtipotransaccion() {
		return idtipotransaccion;
	}

	public void setIdtipotransaccion(int idtipotransaccion) {
		this.idtipotransaccion = idtipotransaccion;
	}

	public int getReferencia() {
		return referencia;
	}

	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", idwallet=" + idwallet + ", idestatustransaccion=" + idestatustransaccion
				+ ", idtipotransaccion=" + idtipotransaccion + ", referencia=" + referencia + ", monto=" + monto
				+ ", concepto=" + concepto + ", fecha=" + fecha + ", canal=" + canal + "]";
	}

	
	
}
