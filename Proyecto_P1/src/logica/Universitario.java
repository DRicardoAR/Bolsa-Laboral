package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Universitario extends Solicitante {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean PostGrado;
	private String Carrera;
	public Universitario(String cedula, String nombres,
			String apellidos, String telefono, LocalDate fechaNacimiento,
			String nacionalidad, String sexo, String estadoCivil,
			String direccion, String provincia, String email,
			boolean vehiculoPropio, int categoriaLicencia,
			int annosExperiencia,ArrayList<String> idiomas, boolean mudarse,boolean PostGrado, String Carrera) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad,
				sexo, estadoCivil, direccion, provincia, email, vehiculoPropio,
				 categoriaLicencia, annosExperiencia, idiomas, mudarse);
		this.PostGrado=PostGrado;
		this.Carrera=Carrera;
	}
	public boolean isPostGrado() {
		return PostGrado;
	}
	public void setPostGrado(boolean postGrado) {
		PostGrado = postGrado;
	}
	public String getCarrera() {
		return Carrera;
	}
	public void setCarrera(String carrera) {
		Carrera = carrera;
	}

}
