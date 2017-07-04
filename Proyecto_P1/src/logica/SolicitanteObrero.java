package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class SolicitanteObrero extends Solicitante {
	ArrayList<String> Habilidades;

	public SolicitanteObrero(String cedula, String nombres, String apellidos,
			String telefono, LocalDate fechaNacimiento, String nacionalidad,
			String sexo, String estadoCivil, String direccion,
			String provincia, String email, boolean vehiculoPropio,
			boolean licencia, int categoriaLicencia, int annosExperiencia,
			String idiomas, boolean mudarse,ArrayList<String> Habilidades) {
		super(cedula, nombres, apellidos, telefono, fechaNacimiento, nacionalidad,
				sexo, estadoCivil, direccion, provincia, email, vehiculoPropio,
				licencia, categoriaLicencia, annosExperiencia, idiomas, mudarse);
		this.Habilidades= new ArrayList<String>();
	}

	public ArrayList<String> getHabilidades() {
		return Habilidades;
	}

	public void setHabilidades(ArrayList<String> habilidades) {
		Habilidades = habilidades;
	}

}
