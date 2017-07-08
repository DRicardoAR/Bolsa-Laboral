package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class Obrero extends Solicitante {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<String> habilidades;

	public Obrero(String cedula, String nombres, String apellidos,
			String telefono, LocalDate fechaNacimiento, String nacionalidad,
			String sexo, String estadoCivil, String direccion,
			String provincia, String email, boolean vehiculoPropio,
			boolean licencia, int categoriaLicencia, int annosExperiencia,
			ArrayList<String> idiomas, boolean mudarse,ArrayList<String> habilidades) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad,
				sexo, estadoCivil, direccion, provincia, email, vehiculoPropio,
				licencia, categoriaLicencia, annosExperiencia, idiomas, mudarse);
		this.habilidades= new ArrayList<>();
	}

	public ArrayList<String> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(ArrayList<String> habilidades) {
		this.habilidades = habilidades;
	}

}
