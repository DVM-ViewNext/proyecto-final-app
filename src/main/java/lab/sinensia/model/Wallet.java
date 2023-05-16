package lab.sinensia.model;

public class Wallet {

	private int id;
	private int idmoneda;
	private int idusuario;
	private String code;
	private double balance;
	private String address;
	private String moneda;

	public Wallet() {

	}

	public Wallet(int idmoneda, int idusuario, String code, double balance, String address, String moneda) {
		super();
		this.idmoneda = idmoneda;
		this.idusuario = idusuario;
		this.code = code;
		this.balance = balance;
		this.address = address;
		this.moneda = moneda;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdmoneda() {
		return idmoneda;
	}

	public void setIdmoneda(int idmoneda) {
		this.idmoneda = idmoneda;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", idmoneda=" + idmoneda + ", idusuario=" + idusuario + ", code=" + code
				+ ", balance=" + balance + ", address=" + address + ", moneda=" + moneda + "]";
	}

	

}
