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
	private String sector;
	private String ciudad;
	private String calle;
	private int numeroLocal;
	private String referencia;
	private ArrayList<Solicitante>misContratados;



	public Empresa(String rNC, String nombre, String telefono, String email, String area, String direccion,
			String sector, String ciudad, String calle, int numeroLocal, String referencia) {
		super();
		RNC = rNC;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.area = area;
		this.direccion = direccion;
		this.sector = sector;
		this.ciudad = ciudad;
		this.calle = calle;
		this.numeroLocal = numeroLocal;
		this.referencia = referencia;
		this.misContratados = new ArrayList<>();
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


	public String getSector() {
	return sector;
}
public void setSector(String sector) {
	this.sector = sector;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
public String getCalle() {
	return calle;
}
public void setCalle(String calle) {
	this.calle = calle;
}
public int getNumeroLocal() {
	return numeroLocal;
}
public void setNumeroLocal(int numeroLocal) {
	this.numeroLocal = numeroLocal;
}
public String getReferencia() {
	return referencia;
}
public void setReferencia(String referencia) {
	this.referencia = referencia;
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
