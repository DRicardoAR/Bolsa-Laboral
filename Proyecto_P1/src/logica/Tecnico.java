package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tecnico extends Solicitante {
	private String area;

	public Tecnico(String cedula, String nombres, String apellidos,
			String telefono, LocalDate fechaNacimiento, String nacionalidad,
			String sexo, String estadoCivil, String direccion,
			String provincia, String email, boolean vehiculoPropio,
			boolean licencia, int categoriaLicencia, int annosExperiencia,
			String idiomas, boolean mudarse,String area) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad,
				sexo, estadoCivil, direccion, provincia, email, vehiculoPropio,
				licencia, categoriaLicencia, annosExperiencia, idiomas, mudarse);
	
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
}
