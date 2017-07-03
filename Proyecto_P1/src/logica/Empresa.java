package logica;

public class Empresa {
	private String RNC;
	private String Nombre;
	private String telefono;
	private String email;
	private String area;
	private String direccion;

	public Empresa(String rNC, String nombre, String telefono, String email, String area, String direccion) {
		super();
		RNC = rNC;
		Nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.area = area;
		this.direccion = direccion;
	}

	public String getRNC() {
		return RNC;
	}

	public void setRNC(String rNC) {
		RNC = rNC;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
