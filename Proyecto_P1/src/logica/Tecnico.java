package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tecnico extends Solicitante {
	private static final long serialVersionUID = 1L;
	private String area;
	public Tecnico(String cedula, String nombres, String apellidos, String telefono, LocalDate fechaNacimiento,
			String nacionalidad, String sexo, String estadoCivil, String direccion, String provincia, String email,
			boolean vehiculoPropio, int categoriaLicencia, int annosExperiencia,
			ArrayList<String> idiomas, boolean mudarse, String area) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
				email, vehiculoPropio, categoriaLicencia, annosExperiencia, idiomas, mudarse);
		this.area = area;
		
	}

	/**
	 * 
	 */
	

	

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
