package logica;

import java.time.LocalDate;

public class SolicitanteUniversitario extends Solicitante {
	boolean PostGrado;
	String Carrera;
	public SolicitanteUniversitario(String cedula, String nombres,
			String apellidos, String telefono, LocalDate fechaNacimiento,
			String nacionalidad, String sexo, String estadoCivil,
			String direccion, String provincia, String email,
			boolean vehiculoPropio, boolean licencia, int categoriaLicencia,
			int annosExperiencia, String idiomas, boolean mudarse,boolean PostGrado, String Carrera) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad,
				sexo, estadoCivil, direccion, provincia, email, vehiculoPropio,
				licencia, categoriaLicencia, annosExperiencia, idiomas, mudarse);
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
