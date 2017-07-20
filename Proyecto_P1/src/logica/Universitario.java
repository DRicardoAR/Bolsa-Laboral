package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Universitario extends Solicitante {
	
	public Universitario(String cedula, String nombres, String apellidos, String telefono, LocalDate fechaNacimiento,
			String nacionalidad, String sexo, String estadoCivil, String direccion, String provincia, String email,
			boolean vehiculoPropio, int categoriaLicencia, int annosExperiencia, ArrayList<String> idiomas,
			boolean contratado, boolean mudarse, String ciudad, String sector, String calle, int numeroCasa,
			String referencia, boolean PostGrado, String Carrera) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
				email, vehiculoPropio, categoriaLicencia, annosExperiencia, idiomas, contratado, mudarse, ciudad, sector, calle,
				numeroCasa, referencia);
		this.PostGrado = PostGrado;
		this.Carrera = Carrera;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean PostGrado;
	private String Carrera;

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
