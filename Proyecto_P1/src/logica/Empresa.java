package logica;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;

public class Empresa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RNC;
	private String nombre;
	private String telefono;
	private String email;
	private String area;
	private String direccion;
	private ArrayList<Solicitante>misContratados;

	public Empresa(String RNC, String nombre, String telefono, String email, String area, String direccion) {
		super();
		this.RNC = RNC;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.area = area;
		this.direccion = direccion;
		misContratados = new ArrayList<>();
	}

	public ArrayList<Solicitante> getMisContratados() {
		return misContratados;
	}
public void insertContratado(Solicitante trabajador){
	LocalDate date = LocalDate.now();
	trabajador.setFechaContratado(date);
	trabajador.setContratado(true);
	misContratados.add(trabajador);
}

	public String getRNC() {
		return RNC;
	}

	public void setRNC(String rNC) {
		RNC = rNC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
